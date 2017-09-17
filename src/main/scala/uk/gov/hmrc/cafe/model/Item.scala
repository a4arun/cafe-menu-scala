package main.scala.uk.gov.hmrc.cafe.model

trait Item {
  val name: String
    val price: Double
    var itemType: ItemType.Value = ItemType.COLD;
}