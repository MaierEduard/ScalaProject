
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SE extends Simulation {

	val httpProtocol = http
		.baseUrl("https://cheeze-flight-booker.herokuapp.com")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:67.0) Gecko/20100101 Firefox/67.0")
    .silentResources
//    .silentUri(".+bookings")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "image/webp,*/*")



	val scn = scenario("SE")
		.exec(http("Home Page")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/favicon.ico")
			.headers(headers_1)
      .check(status.in(200,201,202,304))
      .check(status.not(404))))
		.pause(10)
		.exec(http("SearchFlight")
			.get("/flights?utf8=%E2%9C%93&from=1&to=2&date=2015-01-03&num_passengers=2&commit=search")
			.headers(headers_0)
			.resources(http("request_3")
			.get("/favicon.ico")
			.headers(headers_1).silent))
		.pause(10)
		.exec(http("SelectPlight")
			.get("/bookings/new?utf8=%E2%9C%93&flight_id=10&commit=Select+Flight&num_passengers=2")
			.headers(headers_0)
			.resources(http("request_5")
			.get("/favicon.ico")
			.headers(headers_1).silent))
		.pause(10)
		.exec(http("BookFlight")
			.post("/bookings")
			.headers(headers_0)
			.formParam("utf8", "✓")
			.formParam("authenticity_token", "gFz+TiFmb2pLsaDGGnHQqrrCwJjbJDv2wvV2aaLsBaslYx0k568u2c4dFjzo1tMCMa5EGnEXkuToiVw0pPgi3g==")
			.formParam("booking[flight_id]", "10")
			.formParam("booking[passengers_attributes][0][name]", "bimba")
			.formParam("booking[passengers_attributes][0][email]", "bimba@gmail.com")
			.formParam("booking[passengers_attributes][1][name]", "loty")
			.formParam("booking[passengers_attributes][1][email]", "loty@gmail.com")
			.formParam("commit", "Book Flight")
			.resources(http("request_7")
			.get("/favicon.ico")
			.headers(headers_1).silent)).pause(10)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}