package example.tweet.stream.process

import scala.language.postfixOps

/**
 * TwitterStreamer is the main class of the application.
 * The purpose of this class is to initialize and begin/end
 * the twitter streaming and processing operation.
 */
object TwitterStreamer extends App {

  val twitterStream= new TwitterStreamingInitializer("<Text to Filter Streaming tweets>")

  twitterStream beginStreaming

  Thread.sleep(10000)

  twitterStream endStreaming
}
