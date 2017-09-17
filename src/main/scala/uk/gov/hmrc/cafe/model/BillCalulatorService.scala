package main.scala.uk.gov.hmrc.cafe.model

trait BillCalulatorService {

  	def calculateStandard( order : List[Item]) : Double
	def calculateTotal( order : List[Item]) : Double
}