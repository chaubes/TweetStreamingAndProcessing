package example.tweet.stream.process.common

/**
 * StreamingService declares the methods to control
 * the streaming start and stop operations. This trait
 * needs to provide a custom implementation for these
 * control methods.
 */
trait StreamingService extends BaseService {
  case object StartStreaming
  case object EndStreaming

  protected def beginStreaming

  protected def endStreaming


}