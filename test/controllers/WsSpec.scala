package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

/**
 */
class WsSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {


      play.api.test.WsTestClient.withClient { ws =>
        ws.url("http://localhost:9000").get()
      }


    }

  }
}
