package example.tweet.stream.process

import akka.actor.{Actor, ActorLogging, ActorRef}

/**
 *
 * @param tweetConsumer
 * TweetProcessor akka actor is responsible for processing the tweet
 * and sending back the TweetAnalysisResponse to the TweetConsumer
 */
class TweetProcessor(tweetConsumer: ActorRef) extends Actor with ActorLogging{

  override def receive: Receive = {

    case (tweet: Tweet, trackText: String) =>
    log.info("Tweet Processed  by TweetProcessor...Now sending back the processed response")
    tweetConsumer ! TweetAnalysisResponse(tweet.text+"-> Tweet processed response")
  }
}
