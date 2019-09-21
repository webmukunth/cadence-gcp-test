package computerdatabase

import java.util.UUID.randomUUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://webhook.site")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  private val scn = scenario("BasicSimulation")
    .exec { session =>
      val uid = randomUUID().toString
      val newsession = session.set("rqUID", uid)
      println("Before: " + newsession)
      newsession
    }
    .exec(
      http("home")
        .post("/3fdb4dfb-380d-4f85-9e1f-3c4e1f536968")
        .body( ElFileBody("/tmp/test"))
    )
    .exec { session =>
      println("After: " + session)
      session
    }

  setUp(
    scn.inject(
      atOnceUsers(1)
    )
  ).protocols(httpProtocol)

  before {
    println("About to start")
  }

  after {
    println("Done")
  }
}