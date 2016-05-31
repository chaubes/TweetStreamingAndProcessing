package example.tweet.stream.process.common

import akka.http.scaladsl.model.HttpHeader.ParsingResult
import akka.http.scaladsl.model.{HttpHeader, HttpMethod}
import com.hunorkovacs.koauth.domain.KoauthRequest
import com.hunorkovacs.koauth.service.consumer.DefaultConsumerService

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

/**
 * OAuth trait defines the methods to generate the OAuthHttpHeaders
 * required to authorize the twitter api call. This trait hides
 * the complexity involved in getting the OAuthHttpHeaders from the
 * developer. This methods defined in this trait can be re-used
 * in any application which involves getting the OAuthHttpHeaders
 * based on the Consumer and Token keys and values.
 */
trait OAuth extends BaseService{
  case class Consumer(key: String, secret: String)
  case class Token(value: String, secret: String)

  protected def oAuthConsumer: DefaultConsumerService

  protected def getOAuthHttpHeaders(
                                    consumer: Consumer,
                                    token: Token,
                                    body: String,
                                    httpMethod: HttpMethod,
                                    url: String
                                    ) : Either[String,List[HttpHeader]] = {

    val oAuthHeader : Try[String] = getOAuthHeaderToken(consumer, token,
    body, httpMethod, url)

    oAuthHeader match {
      case Success(header) => val httpHeaders: List[HttpHeader] = List(
        HttpHeader.parse("Authorization", header) match {
          case ParsingResult.Ok(h, _) => Some(h)
          case _ => None
        },
        HttpHeader.parse("Accept", "*/*") match {
          case ParsingResult.Ok(h, _) => Some(h)
          case _ => None
        }
      ).flatten
        Right(httpHeaders)
      case Failure(e) => println(e.getMessage)
                        Left(e.getMessage)
    }

  }

  protected def getOAuthHeaderToken(
                           consumer: Consumer,
                           token: Token,
                           body: String,
                           httpMethod: HttpMethod,
                           url: String
                           ) : Try[String] = {

      val futureOauth :Future[String] =
        oAuthConsumer.createOauthenticatedRequest(
          KoauthRequest(
            method = "POST",
            url = url,
            authorizationHeader = None,
            body = Some(body)
          ),
          consumer.key,
          consumer.secret,
          token.value,
          token.secret
        ) map (_.header)
    Await.ready(futureOauth, Duration.Inf).value.get

  }
}
