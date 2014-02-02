name := "omniprop"

organization := "com.joescii"

version := "0.0.1"

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.10.3", "2.9.2", "2.9.1-1", "2.9.1")

libraryDependencies ++= {
  Seq("org.scalatest" %% "scalatest" % "1.9.1" % "test")
}

scalacOptions <<= scalaVersion map { v: String =>
  val opts = "-deprecation" :: "-unchecked" :: Nil
  if (v.startsWith("2.9.")) opts else opts ++ ("-feature" :: "-language:postfixOps" :: Nil)
}

publishTo <<= version { _.endsWith("SNAPSHOT") match {
    case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
}

credentials += Credentials( file("sonatype.credentials") )

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
        <url>https://github.com/barnesjd/omniprop</url>
        <licenses>
            <license>
              <name>Apache 2.0 License</name>
              <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
              <distribution>repo</distribution>
            </license>
         </licenses>
         <scm>
            <url>git@github.com:barnesjd/omniprop.git</url>
            <connection>scm:git:git@github.com:barnesjd/omniprop.git</connection>
         </scm>
         <developers>
            <developer>
              <id>barnesjd</id>
              <name>Joe Barnes</name>
              <url>https://github.com/barnesjd</url>
            </developer>
         </developers>
 )