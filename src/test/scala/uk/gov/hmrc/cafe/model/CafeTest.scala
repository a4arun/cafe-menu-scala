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
   
    val colaAndCoffeOrder = List[Item] ( new Drink("Cola",.5,ItemType.COLD)  ,
                              new Food("Coffee",1.00,ItemType.HOT) 
    )

    assertThat(calculator.calculateStandard(colaAndCoffeOrder), is(1.50));
  }
}