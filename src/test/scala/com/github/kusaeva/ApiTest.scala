package com.github.kusaeva

import akka.http.scaladsl.model.headers.{BasicHttpCredentials, HttpChallenge}
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes, StatusCodes}
import akka.http.scaladsl.model.headers.`WWW-Authenticate`

import akka.http.scaladsl.server.Route
import akka.util.ByteString
import domain.{Buy, Ticker, TradingPair, Transaction}
import json.JsonSupport

class ApiTest extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport {
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
