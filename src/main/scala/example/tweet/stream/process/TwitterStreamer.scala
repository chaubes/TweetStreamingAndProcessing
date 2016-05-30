package example.tweet.stream.process

import scala.language.postfixOps


object TwitterStreamer extends App {

  val twitterStream= new TwitterStreamingInitializer("shahrukh")

  twitterStream beginStreaming

  Thread.sleep(10000)

  twitterStream endStreaming
}
