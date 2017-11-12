package typesafeconfig

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

/**
  * https://github.com/lightbend/config#optional-system-or-env-variable-overrides
  * https://stackoverflow.com/questions/38197406/overriding-configuration-with-environment-variables-in-typesafe-config
  *
  */
class TypesafeConfigSpec2 extends FlatSpec with Matchers {

  "typesafe config with override" should "work" in {

    val config = ConfigFactory.load("application2")

    /**
      * in build.sbt:
      * fork in Test := true
      * envVars in Test := Map("envbasedir" -> "foo")
      * in intellij:
      * ScalaTest default: VM Parameter -Denvbasedir=foo, or environment variable envbasedir=foo
      */
    config.getString("basedir") shouldBe "foo"


  }
}
