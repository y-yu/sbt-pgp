package com.jsuereth.pgp
package app

import cli._

import sbt.complete.DefaultParsers

import xsbti.{ AppMain, AppConfiguration }

object App {
  def apply(args: Array[String]) : Int = {
    val (ctx, rargs) = Config.readArgs(args)
    val parser = PgpCommand.parser(ctx)
    val cmdString = args mkString " "
    val parsed = (DefaultParsers(parser)(cmdString)) 
    parsed.result match {
      case Some(cmd) =>
        cmd run ctx
      case None =>
        System.err.println("Invalid Command: " + cmdString)
        System.err.println("Did you mean: ")
        for(completion <- parsed.completions(5).get) {
          System.err.println(">  " + completion.display)
        }
        sys.error("Unable to complete spgp command!")
    }
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
