package graphite

sealed trait Node

object Node {

  case object * extends Node
  case object ? extends Node

}

