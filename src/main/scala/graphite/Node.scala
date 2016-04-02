package graphite

sealed trait Node

object Node {

//  private[graphite] sealed trait ? extends Node
//  private[graphite]sealed trait * extends Node

  case object * extends Node
  case object ? extends Node

}

