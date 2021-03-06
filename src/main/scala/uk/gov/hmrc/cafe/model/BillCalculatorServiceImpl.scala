package uk.gov.hmrc.cafetest.model

import main.scala.uk.gov.hmrc.cafe.model.Item
import main.scala.uk.gov.hmrc.cafe.model.Drink
import main.scala.uk.gov.hmrc.cafe.model.BillCalulatorService
import main.scala.uk.gov.hmrc.cafe.model.Food
import main.scala.uk.gov.hmrc.cafe.model.ItemType

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

  def containsHotFood(order: List[Item]): Boolean = {

    var hotFood: Boolean = false;

    order.foreach { item =>
      {
        if (item.isInstanceOf[Food] && item.itemType == ItemType.HOT) {
          hotFood = true
        }
      }
    }

    return hotFood;

  }

  def addServiceCharge(order: List[Item]): Double = {

    var standardBill: Double = calculateStandard(order)

    var serviceChargeRate: Double = 0;
    if (!containsDrinkOnly(order)) serviceChargeRate = .1;
    if (containsHotFood(order)) serviceChargeRate = .2;

    var totalServiceCharge: Double = standardBill * serviceChargeRate

    var totalBill: Double = standardBill + standardBill * serviceChargeRate;

    if (totalServiceCharge >= 20) {

      return standardBill + 20;
    } else {

      var totalBill: Double = standardBill + standardBill * serviceChargeRate;

      return totalBill

    }

  }

}