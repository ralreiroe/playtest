package controllers

import javax.inject._

import play.api._
import play.api.libs.json.JsValue
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, _}

import scala.concurrent.Future

/**
  * curl -v -X POST --header "X-requested-with: foo" --data-binary "@/i/p/ralfoenning/playtest/test/resources/prod-bu-total-170313-cleans.txt" http://localhost:9000/etmp-data/test
  *
  * cd /i/p/ralfoenning/playtest/; curl -v -X POST --header "Content-Type: application/json" --data "@./test/resources/jsontest.json" http://localhost:9000/jsontest
  *
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def loadBusinessUsers = {
    Logger.info("Import business users - dry-run")
    Action.async(parse.tolerantText) { implicit request =>
      println(request.body)
      Future.successful(Ok)
    }
  }


  case class Person(name: String, country: String, id: Int)

  import play.api.libs.json.{ JsError, JsString, JsSuccess, Reads }
  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val personReads = (
    (__ \ 'name).read[String] and
      (__ \ 'country).read[String] and
      (__ \ 'id).read[Int]
    )(Person)

  def parseJson = Action.async { implicit request: Request[AnyContent] =>

    val json = request.body.asJson.get

    val person = json.as[Person]


    Future.successful(Ok("name : " + person.name))
  }
}
