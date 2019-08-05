package repository

import domain.Transaction
import domain.utils.TransactionId
import service.TransactionRepository

object SimpleRepository extends TransactionRepository {
  var transactions = collection.mutable.Map[TransactionId, Transaction]()

  override def create(transaction: Transaction): Transaction = {
    transactions += transaction.id -> transaction
    transaction
  }

  override def update(transaction: Transaction): Option[Transaction] = {
    transactions(transaction.id) = transaction
    Some(transaction)
  }

  override def get(id: TransactionId): Option[Transaction] = {
    transactions.get(id)
  }

  override def delete(id: TransactionId): Option[Transaction] = {
    val tr = transactions.get(id)
    transactions -= id
    tr
  }
}
