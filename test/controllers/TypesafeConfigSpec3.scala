package controllers

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

/**
  * requires build.sbt javaOptions += "-Dconfig.file=/i/p/ralfoenning/playtest/test/resources/application2.conf"

  * https://stackoverflow.com/questions/19060623/passing-jvm-args-through-sbt
  */
class TypesafeConfigSpec3 extends FlatSpec with Matchers {

  "typesafe config" should "load application2 based on config.file VM parameter" in {

      val config = ConfigFactory.load()     //loads either application.conf if on classpath or value of -Dconfig.file

      /**
        * run sbt with build.sbt containing
        *   javaOptions += "-Dconfig.file=/i/p/ralfoenning/playtest/test/resources/application2.conf"
        *
        * or in intellij with VM Parameter -Dconfig.file=src/test/application2.conf
        */
    import scala.collection.JavaConverters._
    config.getObject("exchanges").keySet().asScala.toList shouldBe List("warsaw")

    }

}
