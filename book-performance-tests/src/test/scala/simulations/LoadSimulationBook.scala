package simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import scala.concurrent.duration._

class LoadSimulationBook extends Simulation {

  val httpConf: HttpProtocolBuilder = http.baseUrl("http://localhost:8080/v1/")
    .header("Accept", "application/json")

  def getAllBooks() = {
    exec(
      http("Get all books")
        .get("books")
        .check(status.is(200))
    )
  }

  def getBookById() = {
    exec(
      http("Get book by Id")
        .get("books/1")
        .check(status.is(200))
    )
  }

  val scn: ScenarioBuilder = scenario("Fixed Duration Load Simulation")
    .exec(getAllBooks())
    .pause(5)
    .exec(getBookById())
    .pause(5 )
    .exec(getAllBooks());

  setUp(scn.inject(
    rampConcurrentUsers(0) to (500) during (60 seconds),
    constantConcurrentUsers(500) during (10 minutes))
    .protocols(httpConf))
}