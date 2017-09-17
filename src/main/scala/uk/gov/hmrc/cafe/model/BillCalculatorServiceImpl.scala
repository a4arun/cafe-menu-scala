package main.scala.uk.gov.hmrc.cafe.model

class BillCalculatorServiceImpl extends BillCalulatorService {

  def calculateStandard(order: List[Item]): Double = {

    var total: Double = 0

    order.foreach { item => total += item.price }
    return total
  }

  def containsDrinkOnly(order: List[Item]): Boolean = {

    var onlyDrinks: Boolean = true;

    order.foreach { item =>
      {
        if (!item.isInstanceOf[Drink]) {
          onlyDrinks = false
        }
      }
    }

    return onlyDrinks;

  }

}