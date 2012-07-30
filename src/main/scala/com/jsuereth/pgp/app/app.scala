package com.jsuereth.pgp
package app

import cli._

import sbt.complete.DefaultParsers

import xsbti.{ AppMain, AppConfiguration }

object App {
  def apply(args: Array[String]) : Int = {
    val (ctx, rargs) = Config.readArgs(args)
    val parser = PgpCommand.parser(ctx)
    
    (DefaultParsers(parser)(args mkString " ")).result match {
      case Some(cmd) =>
        cmd run ctx
      case None =>
        sys error ("Could not parse command line: " + args)
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
