package trade_statistics

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import json.DomainJsonSupport

class ApiTest extends WordSpec with Matchers with ScalatestRouteTest with DomainJsonSupport {
  val routes = (new Api).routes

  "API" should {
    "GET /status resp OK" in {
      Get("/status") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }

    "POST /status resp OK" in {
      Post("/status") ~> Route.seal(routes) ~> check {
        status.isFailure() shouldBe true
      }
    }

  }
}
