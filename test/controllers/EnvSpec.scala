package controllers

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

/**
  * https://stackoverflow.com/questions/39902049/setting-environment-variables-when-running-scala-sbt-test-suite
  * https://alvinalexander.com/java/how-see-jvm-parameters-arguments-from-running-java-application
  * https://stackoverflow.com/questions/34028195/how-do-i-test-code-that-requires-an-environment-variable
  *

  */
class EnvSpec extends FlatSpec with Matchers {

  "get JVM parameters" should "work" in {
    import java.lang.management.ManagementFactory

    // get a RuntimeMXBean reference
    val runtimeMxBean = ManagementFactory.getRuntimeMXBean

    // get the jvm's input arguments as a list of strings
    val listOfArguments = runtimeMxBean.getInputArguments

    // print the arguments using my logger
    import scala.collection.JavaConverters._

    for (a <- listOfArguments.asScala) println(s"ARG: $a")

  }
  "get env variables" should "work" in {
    import scala.collection.JavaConverters._
    System.getenv().asScala.foreach(println(_))

    val orElse = scala.util.Properties.envOrElse("PWD", "unknown")
    orElse shouldBe "/i/p/ralfoenning/playtest"


  }
}
