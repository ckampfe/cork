package environment

import com.typesafe.config._

class Env(env: String) {
  var Config = ConfigFactory.load(env)

  object Db {
    lazy val adapter  = Config.getString("$name;format="norm"$.database.adapter")
    lazy val host     = Config.getString("$name;format="norm"$.database.host")
    lazy val port     = Config.getString("$name;format="norm"$.database.port")
    lazy val name     = Config.getString("$name;format="norm"$.database.name")
    lazy val user     = Config.getString("$name;format="norm"$.database.user")
    lazy val password = Config.getString("$name;format="norm"$.database.password")

    lazy val rdbmsUrl = s"jdbc:\${adapter}://\${host}:\${port}/\${name}"
    lazy val h2Url    = s"jdbc:h2:mem:${name}"
  }
}
