package main.scala.uk.gov.hmrc.cafe.model

    case class Food(name: String, price: Double) extends Item {

    def this(name: String, price: Double, itemType: ItemType.Value) = {
      this(name, price)
      this.itemType = itemType
    }
    
        override def toString: String = {
      return " Name = " + name +
        ", price  = " + price +
        ", itemType = " + itemType.toString()
    }
  }