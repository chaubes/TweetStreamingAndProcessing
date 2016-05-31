package example.tweet.stream.process

/**
 *
 * @param id
 * @param id_str
 * @param lang
 * @param text
 * case class for holding the tweet parameters
 */
case class Tweet(
                   id: Option[Long],
                   id_str: Option[String],
                   lang: Option[String],
                   text: String
                   )

/**
 *
 * @param text
 * case class for holding the TweetAnalysisResponse
 */
case class TweetAnalysisResponse(text: String)