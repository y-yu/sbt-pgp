package com.jsuereth.app

import xsbti.{ AppMain, AppConfiguration }

object App {
  def apply(args: Array[String]) : Int = {
    println("pgp me")
    0
  }
}

case class Exit(val code: Int) extends xsbti.Exit

class Script extends AppMain {
  def run(conf: AppConfiguration) =
    Exit(App(conf.arguments))
}

object Main {
  def main(args: Array[String]) {
    App(args)
  }
}
