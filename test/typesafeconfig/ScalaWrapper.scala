package typesafeconfig

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

/**
  * https://github.com/kxbmap/configs
  */
class ScalaWrapper extends FlatSpec with Matchers {

  "case class value out of the box" should "work" in {

    import scala.concurrent.duration.FiniteDuration

    case class MyConfig(foo: String, bar: Int, baz: List[FiniteDuration])
    val config = ConfigFactory.parseString(
      """
  my-config {
    foo = My config value
    bar = 123456
    baz = [1h, 2m, 3s]
  }
  """)

    import configs.syntax._

    println(config.get[MyConfig]("my-config"))

  }

}
