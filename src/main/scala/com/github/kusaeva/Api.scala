package com.github.kusaeva

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.BasicHttpCredentials
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives._

import json.JsonSupport
import domain.Transaction
import service.TransactionService
import repository.SimpleRepository

class Api extends JsonSupport {
  val validCredentials = BasicHttpCredentials("John", "p4ssw0rd")

  val service = TransactionService(SimpleRepository)

  def myUserPassAuthenticator(credentials: Credentials): Option[String] =
    credentials match {
      case p @ Credentials.Provided(id) if p.verify("p4ssw0rd") => Some(id)
      case _                                                    => None
    }

  def routes: Route =
    concat(
      path("status") {
        get {
          complete(StatusCodes.OK)
        }
      },
      Route.seal {
        authenticateBasic(realm = "secure site", myUserPassAuthenticator) {
          user =>
            pathPrefix("api" / "v1") {
              post {
                path("create") {
                  decodeRequest {
                    entity(as[Transaction]) { tr =>
                      complete {
                        service.create(tr, user)
                      }
                    }
                  }
                } ~
                  path("update") {

                    decodeRequest {
                      entity(as[Transaction]) { tr =>
                        complete {
                          service.update(tr, user)
                        }
                      }
                    }
                  }
              } ~
                get {
                  path("get" / LongNumber) { id =>
                    complete {
                      service.get(id, user)
                    }
                  } ~
                    path("delete" / LongNumber) { id =>
                      complete {
                        service.delete(id, user)
                      }
                    }
                }
            }
        }
      }
    )
}
