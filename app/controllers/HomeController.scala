package controllers

import javax.inject._

import play.api._
import play.api.libs.json.JsValue
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, _}

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
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

  def parseJson = Action { implicit request: Request[AnyContent] =>

    val json = request.body.asJson.get

    val person = json.as[Person]


    Ok("name : " + person.name)
  }
}
