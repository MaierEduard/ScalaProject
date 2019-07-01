package myFirstPackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class myfirstscript extends Simulation{

  val httpProtocol = http.baseUrl("http://newtours.demoaut.com/").header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")

  val header_1 = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "accept-encoding" -> "gzip, deflate, br",
    "accept-language" -> "en-US,en;q=0.9"
  )
  val scn =scenario("viewCruise").exec(http("req_1").get("mercurycruise.php").headers(header_1)).pause(10);

  setUp(scn.inject(atOnceUsers(2))).protocols(httpProtocol)

}
