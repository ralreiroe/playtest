package typesafeconfig

import java.util

import com.typesafe.config.ConfigFactory
import org.scalatest.{Matchers, WordSpec}

/**
  *
  * https://stackoverflow.com/questions/17598856/iterate-over-fields-in-typesafe-config
  *
  */
class TypesafeConfigSpec extends WordSpec with Matchers {

  "typesafe config iteration" should {

    "work" in {

      List(
        (1234, 17623615123L),
        (1235, 17623615124L),
        (1236, 17623615125L),
        (1237, 17623615126L)
      )

      val config = ConfigFactory.load("application2")

      import scala.collection.JavaConversions._
      val keySetJava: util.Set[String] = config.getObject("exchanges").keySet()

      val keySet = collection.immutable.Set(keySetJava.toList: _*)

      println(keySet.toList)
      keySet.foreach {

        case k => {
          val string = config.getString(s"exchanges.${k}.warsawLEI")
          println(string.replace("$shortcode", "1234").replace("$longcode", "126357165327"))

          val res4 = List(
            (1234, 17623615123L),
            (1235, 17623615124L),
            (1236, 17623615125L),
            (1237, 17623615126L)
          ).map {
            case (s, l) => string.replace("$shortcode", s.toString).replace("$longcode", l.toString)
          }.foreach(println(_))

        }
      }

      //string interpolation
      val height = 1.9d
      val name = "James"
      println(f"$name%s is $height%1.2f meters tall") // James is 1.90 meters tall


    }
  }
}
