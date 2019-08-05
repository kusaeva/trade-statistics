package domain

import utils.{UserId}

case class User(email: String, hash: String, id: Option[UserId] = None)
