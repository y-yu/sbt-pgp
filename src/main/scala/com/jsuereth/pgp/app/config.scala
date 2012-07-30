package com.jsuereth.pgp
package app

import sbt._

object Config {
  
  def readArgs(args: Array[String]): (cli.PgpCommandContext, Array[String]) = {
    val static = ConsolePgpStaticContext(publicKeyFile, secretKeyFile)
    (ConsolePgpCommandContext(static, ConsoleLogger()), args)
  }
  
  
  def secretKeyFile: File = {
    val gnuloc = file(System.getProperty("user.home")) / ".gnupg" / "secring.gpg"
    val ourloc = file(System.getProperty("user.home")) / ".spgp" / "secring.asc"
    // TODO - Allow a default
    if(!gnuloc.exists) ourloc else gnuloc
  }
  
  def publicKeyFile: File = {
    val gnuloc = file(System.getProperty("user.home")) / ".gnupg" / "pubring.gpg"
    val ourloc = file(System.getProperty("user.home")) / ".spgp" / "pubring.asc"
    // TODO - Allow a default
    if(!gnuloc.exists) ourloc else gnuloc
  }
}