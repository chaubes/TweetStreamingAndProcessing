package example.tweet.stream.process

import akka.actor.{Actor, ActorLogging, ActorRef}


class TweetProcessor(tweetConsumer: ActorRef) extends Actor with ActorLogging{

  override def receive: Receive = {

    case (tweet: Tweet, trackText: String) =>
    println("Tweet Processed  by TweetProcessor...Now sending back the processed response")
    tweetConsumer ! TweetAnalysisResponse(tweet.text+"-> Tweet processed response")
  }
}
