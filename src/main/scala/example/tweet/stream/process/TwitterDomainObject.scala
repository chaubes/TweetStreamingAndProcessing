package example.tweet.stream.process

case class Tweet(
                   id: Option[Long],
                   id_str: Option[String],
                   lang: Option[String],
                   text: String
                   )
case class TweetAnalysisResponse(text: String)