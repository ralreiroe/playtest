package controllers

import java.io.File

import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{Matchers, WordSpec}
import play.api.http.Status

/**
 */
class WsSpecPost extends WordSpec with Matchers with ScalaFutures with Eventually {

  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(5, Millis))


  "HomeController GET" should {

    /**
      * Sending an 2xx 'early' response before end of request was received... Note that the connection will be closed after this response. Also, many clients will not read early responses! Consider only issuing this response after the request data has been completely read!

https://www.playframework.com/documentation/2.6.x/WSMigration26

      If you are running a functional test, you can use the play.api.test.WsTestClient, which will start up and shut down a standalone WSClient instance:

      */

    "render the index page from a new instance of controller" in {


      val file = new File("/i/p/ralfoenning/playtest/test/resources/prod-bu-total-170313-cleans.txt")

        val res = play.api.test.WsTestClient.withClient { ws =>
          ws.url("http://localhost:9000/etmp-data/testsync").post(file)
        }

      val wSResponse = eventually {
        res.futureValue
      }
      wSResponse.status shouldBe Status.OK

      println(wSResponse.body)





    }

  }
}
