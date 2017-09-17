package main.scala.uk.gov.hmrc.cafe.model

trait BillCalulatorService {

  def calculateStandard(order: List[Item]): Double
  def addServiceCharge(order: List[Item]): Double
}