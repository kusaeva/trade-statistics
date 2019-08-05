package com.github.kusaeva

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.{BasicHttpCredentials, HttpChallenge, `WWW-Authenticate`}

class ApiAuthTest extends ApiTest {
  val validCredentials   = BasicHttpCredentials("John", "p4ssw0rd")
  val invalidCredentials = BasicHttpCredentials("Peter", "pan")

  Get("/api/v1/create") ~> routes ~> check {
    status shouldEqual StatusCodes.Unauthorized
    responseAs[String] shouldEqual "The resource requires authentication, which was not supplied with the request"
    header[`WWW-Authenticate`].get.challenges.head shouldEqual HttpChallenge(
      "Basic",
      Some("secure site"),
      Map("charset" -> "UTF-8")
    )
  }

  Get("/api/v1/create") ~>
    addCredentials(invalidCredentials) ~> // adds Authorization header
    routes ~> check {
    status shouldEqual StatusCodes.Unauthorized
    responseAs[String] shouldEqual "The supplied authentication is invalid"
    header[`WWW-Authenticate`].get.challenges.head shouldEqual HttpChallenge(
      "Basic",
      Some("secure site"),
      Map("charset" -> "UTF-8")
    )
  }

}
