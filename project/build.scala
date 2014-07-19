import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object CorkBuild extends Build {
  val Organization = "com.belly"
  val Name = "Cork"
  val Version = "0.0.1"
  val ScalaVersion = "2.11.1"
  val ScalatraVersion = "2.3.0"

  lazy val project = Project (
    "cork",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra"           %% "scalatra"             % ScalatraVersion,
        "org.scalatra"           %% "scalatra-scalate"     % ScalatraVersion,
        "org.scalatra"           %% "scalatra-specs2"      % ScalatraVersion % "test",
        "ch.qos.logback"          % "logback-classic"      % "1.0.6" % "runtime",
        "org.scalatra"           %% "scalatra-json"        % "2.3.0",
        "org.json4s"             %% "json4s-jackson"       % "3.2.10",
        "com.github.nscala-time" %% "nscala-time"          % "1.2.0",
        "org.scalikejdbc"        %% "scalikejdbc"          % "2.0.+",
        "mysql"                   % "mysql-connector-java" % "5.1.31",
        // "com.h2database"          %  "h2"                  % "1.4.+",
        "ch.qos.logback"          %  "logback-classic"     % "1.1.+",

        "org.eclipse.jetty"       % "jetty-webapp"     % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet"    % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}