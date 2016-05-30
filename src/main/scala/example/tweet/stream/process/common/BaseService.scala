package example.tweet.stream.process.common

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol

import scala.concurrent.{ExecutionContextExecutor, Future}

trait BaseService extends DefaultJsonProtocol with SprayJsonSupport {
  protected implicit def serviceName: String
  protected implicit def system: ActorSystem
  protected implicit def materializer: ActorMaterializer
  protected implicit def executor: ExecutionContextExecutor
  protected implicit def log: LoggingAdapter = Logging(system, serviceName)

  protected def processHttpPOSTRequest(body: String,
                             url: String,
                             httpHeaders: List[HttpHeader],
                             responseParsingFunction: (Future[HttpResponse]) => Unit) : Unit = {
    val source = Uri(url)

    val httpPostRequest: HttpRequest = HttpRequest(
      method = HttpMethods.POST,
      uri = source,
      headers = httpHeaders,
      entity = HttpEntity(contentType = ContentType(MediaTypes.`application/x-www-form-urlencoded`, HttpCharsets.`UTF-8`), string = body)
    )
    val request = Http().singleRequest(httpPostRequest)
    responseParsingFunction(request)
  }
}
