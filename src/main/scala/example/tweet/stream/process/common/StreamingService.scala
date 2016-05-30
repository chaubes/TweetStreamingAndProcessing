package example.tweet.stream.process.common

trait StreamingService extends BaseService {
  case object StartStreaming
  case object EndStreaming

  protected def beginStreaming

  protected def endStreaming


}