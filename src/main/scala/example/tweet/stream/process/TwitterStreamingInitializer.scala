package example.tweet.stream.process

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.HttpMethods
import akka.stream.ActorMaterializer
import com.hunorkovacs.koauth.service.consumer.DefaultConsumerService

import scala.concurrent.ExecutionContextExecutor


class TwitterStreamingInitializer(textInput: String) extends TwitterStreamingService{
  override protected implicit val system: ActorSystem = ActorSystem(serviceName)
  override protected implicit val materializer: ActorMaterializer = ActorMaterializer()
  override protected implicit val executor: ExecutionContextExecutor = system.dispatcher
  override protected implicit val oAuthConsumer = new DefaultConsumerService(executor)

  override val consumerRef = system.actorOf(Props(new TweetConsumer()), name = "tweetConsumer")
  override val trackText = textInput

  override def beginStreaming() : Unit = {
    println(s"$serviceName#################Beginning to Stream for $textInput")
    val body = s"track=$textInput&lang=en"
    getOAuthHttpHeaders(consumer, token, body, HttpMethods.POST, url) match {
      case Right(httpHeaders) => processHttpPOSTRequest(body, url, httpHeaders, tweetStreamProcessingFunction)
      case Left(error) => println(s"Error occured $error")
    }
  }

  override def endStreaming() : Unit = {
    println(s"$serviceName#################Shutting down Stream for $textInput")
    materializer.shutdown()
    system.shutdown()
  }
}
