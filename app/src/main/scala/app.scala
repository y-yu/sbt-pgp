package com.jsuereth.app

import xsbti.{ AppMain, AppConfiguration }

object App {
  def apply(args: Array[String]) {
    // pgp stuff here
  }
}

case class Exit(val code: Int) extends xsbti.Exit(code)

class Script extends AppMain {
  def run(conf: AppConfiguration) =
    Exit(App(conf.arguments))
}

object Main {
  def main(arg: Array[String]) {
    App(args)
  }
}
