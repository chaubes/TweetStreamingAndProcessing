package example.tweet.stream.process

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import example.tweet.stream.process.common.{Config, OAuth, StreamingService}
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
 * TwitterStreamingService provides a custom implementation for initializing
 * the access token properties for Twitter streaming api.
 * It also provides a definition of a callback method tweetStreamProcessingFunction
 * for performing actions on the captured streaming tweets.
 */
trait TwitterStreamingService extends StreamingService with Config with OAuth{
 case class TwitterStreamMessage(textInput: String)

  protected val serviceName = "TwitterStreamingService"
  implicit val formats = DefaultFormats

  val consumer = Consumer(services.getString("twitterAppConsumerKey"), services.getString("twitterAppConsumerSecret"))
  val token = Token(services.getString("twitterAppAccessToken"), services.getString("twitterAppAccessTokenSecret"))

  val url = services.getString("twitterStreamUrl")

  protected def consumerRef: ActorRef
  protected def trackText: String

  /**
   *
   * @param request
   * tweetStreamProcessingFunction method is a callback method for performing
   * actions on the captured streaming tweets
   */
  protected def tweetStreamProcessingFunction(request: Future[HttpResponse]) : Unit = {
    request.flatMap { response =>
      if (response.status.intValue() != 200) {
        println(response.entity.dataBytes.runForeach(_.utf8String))
        Future(Unit)
      } else {
        response.entity.dataBytes
          .scan("")((acc, curr) => if (acc.contains("\r\n")) curr.utf8String else acc + curr.utf8String)
          .filter(_.contains("\r\n"))
          .map(json => Try(parse(json).extract[Tweet]))
          .runForeach {
            case Success(tweet) =>
              consumerRef ! (tweet, trackText)
            case Failure(e) =>
              consumerRef ! e
          }
      }
    }
  }


}
