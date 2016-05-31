package example.tweet.stream.process

import akka.actor.{Actor, ActorLogging, Props}
import akka.routing.FromConfig

/**
 * TweetConsumer akka actor is responsible for forwarding the captured
 * tweets to a tweetProcessor router which processes the tweets asynchronously
 * among a pool of actors and returns the TweetAnalysisResponse. This class is also responsible to
 * handle the received TweetAnalysisResponse.
 */
class TweetConsumer extends Actor with ActorLogging{

  val actorRouter = context.actorOf(FromConfig.props(Props(new TweetProcessor(self))), "tweetProcessor")

  override def receive: Receive = {
    case (tweet: Tweet, trackText: String) =>
      actorRouter ! (tweet, trackText)
    case tweetAnalysisResponse: TweetAnalysisResponse =>{
      log.info("TweetConsumer Received TweetAnalysisResponse : "+ tweetAnalysisResponse.text)
    }

    case e: Throwable =>
      log.error("Actor Error-----")
      log.error(e.getMessage)
  }
}
