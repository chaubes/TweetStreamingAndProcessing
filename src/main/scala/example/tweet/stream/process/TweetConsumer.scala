package example.tweet.stream.process

import akka.actor.{Actor, ActorLogging, Props}
import akka.routing.FromConfig


class TweetConsumer extends Actor with ActorLogging{

  val actorRouter = context.actorOf(FromConfig.props(Props(new TweetProcessor(self))), "tweetProcessor")

  override def receive: Receive = {
    case (tweet: Tweet, trackText: String) =>
      actorRouter ! (tweet, trackText)
    case tweetAnalysisResponse: TweetAnalysisResponse =>{
      println("TweetConsumer Received TweetAnalysisResponse : "+ tweetAnalysisResponse.text)
    }

    case e: Throwable =>
      println("Actor Error-----")
      println(e.getMessage)
  }
}
