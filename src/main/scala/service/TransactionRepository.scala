package service

import domain.Transaction
import domain.utils.TransactionId

trait TransactionRepository {
  def create(transaction: Transaction): Transaction
  def update(transaction: Transaction): Option[Transaction]
  def get(id: TransactionId): Option[Transaction]
  def delete(id: TransactionId): Option[Transaction]
}
