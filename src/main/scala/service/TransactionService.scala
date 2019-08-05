package service

import domain.Transaction
import domain.utils.{TransactionId, UserId}

class TransactionService(repository: TransactionRepository) {
  def create(transaction: Transaction, userId: UserId): Transaction =
    repository.create(transaction)

  def update(transaction: Transaction, userId: UserId): Option[Transaction] =
    repository.update(transaction)

  def get(id: TransactionId, userId: UserId): Option[Transaction] =
    repository.get(id)

  def delete(id: TransactionId, userId: UserId): Option[Transaction] =
    repository.delete(id)
}

object TransactionService {
  def apply(repository: TransactionRepository): TransactionService =
    new TransactionService(repository)
}
