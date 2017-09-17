package test.scala.uk.gov.hmrc.cafe.model
import org.scalatest.junit.JUnitSuite
import scala.collection.mutable.ListBuffer
import org.junit.Assert._
import org.junit.Before
import main.scala.uk.gov.hmrc.cafe.model.BillCalulatorService
import main.scala.uk.gov.hmrc.cafe.model.BillCalculatorServiceImpl
import main.scala.uk.gov.hmrc.cafe.model.Item
import main.scala.uk.gov.hmrc.cafe.model.Drink
import org.hamcrest.CoreMatchers.is;
import main.scala.uk.gov.hmrc.cafe.model.ItemType
import main.scala.uk.gov.hmrc.cafe.model.Food

class CafeTest extends JUnitSuite {

  var calculator: BillCalulatorService = _

  @Before def initialize() {
    calculator = new BillCalculatorServiceImpl()
  }

  @Test def noOrderTest() {
    val noOrder = List[Item]()
    assertThat(calculator.calculateStandard(noOrder), is(0.0));
  }

  @Test def colaOrderTest() {
    val colaOrder = List[Item](new Drink("Cola", .5, ItemType.COLD))

    assertThat(calculator.calculateStandard(colaOrder), is(0.5));
  }

  @Test def cofeeOrderTest() {
    val coffeeOrder = List[Item](new Drink("Coffee", 1.00, ItemType.HOT))

    assertThat(calculator.calculateStandard(coffeeOrder), is(1.00));
  }

  @Test def colaAndCoffeOrderTest() {

    val colaAndCoffeOrder = List[Item](new Drink("Cola", .5, ItemType.COLD),
      new Food("Coffee", 1.00, ItemType.HOT))

    assertThat(calculator.calculateStandard(colaAndCoffeOrder), is(1.50));
  }

  @Test def cheeseSandwichOrderTest() {
    val cheeseSandwichOrder = List[Item](new Drink("Cheese Sandwich", 2.00, ItemType.COLD))

    assertThat(calculator.calculateStandard(cheeseSandwichOrder), is(2.00));
  }

  @Test def steakSandwichOrderTest() {
    val steakSandwichOrder = List[Item](new Drink("Steak Sandwich", 4.50, ItemType.HOT))

    assertThat(calculator.calculateStandard(steakSandwichOrder), is(4.50));
  }

  @Test def allFoodOrderTest() {

    val allFoodOrder = List[Item](new Drink("Cheese Sandwich", 2.00, ItemType.COLD),
      new Food("Steak Sandwich", 4.5, ItemType.HOT))

    assertThat(calculator.calculateStandard(allFoodOrder), is(6.50));
  }

  @Test def allFoodOrderTest() {
    val allOrder = List[Item](new Drink("Cola", .5, ItemType.COLD),
      new Food("Steak Sandwich", 4.5, ItemType.HOT), new Food("Cheese Sandwich", 2.0, ItemType.COLD), new Drink("Coffee", 1.00, ItemType.HOT))
    assertThat(calculator.calculateStandard(allOrder), is(8.00));
  }

  @Test def noServiceChargeAppliedonDrinkMenuTest() {
    val allOrder = List[Item](new Drink("Cola", .5, ItemType.COLD),
      new Drink("Coffee", 1.00, ItemType.HOT))

    assertThat(calculator.addServiceCharge(allOrder), is(1.50));
  }

  @Test def serviceChargeAppliedOnAnyFoodOrderTest() {

    val anyFoodOrder = List[Item](new Drink("Cola", .5, ItemType.COLD),
      new Food("Cheese Sandwich", 2.0, ItemType.COLD))

    assertThat(calculator.addServiceCharge(anyFoodOrder), is(2.75));

  }

  @Test def serviceChargeAppliedonAnyHootFoodOrderTest() {

    val drinksAndHotFoodOrder = List[Item](new Drink("Cola", .5, ItemType.COLD),
      new Food("Steak Sandwich", 4.5, ItemType.HOT))

    assertThat(calculator.addServiceCharge(drinksAndHotFoodOrder), is(6.00));

  }
}