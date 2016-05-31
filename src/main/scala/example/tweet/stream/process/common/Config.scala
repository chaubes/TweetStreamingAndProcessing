package example.tweet.stream.process.common


import com.typesafe.config.ConfigFactory

/**
 * Config trait represents the properties value provider
 * for the application. All the properties defined in the application.conf
 * can be accessed by extending the Config trait.
 */
trait Config{
  private val config = ConfigFactory.load()
  val services = config.getConfig("services")
}
