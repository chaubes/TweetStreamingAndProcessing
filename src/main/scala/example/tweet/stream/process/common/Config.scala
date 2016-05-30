package example.tweet.stream.process.common


import com.typesafe.config.ConfigFactory

trait Config{
  private val config = ConfigFactory.load()
  val services = config.getConfig("services")
}
