package com.jsuereth
package pgp

import sbt._

object packageobject {
  /** Default extension for PGP signatures. */
  val gpgExtension = ".asc"

  /** Reads the passphrase from the console. */
  def readPassphrase(): Array[Char] = System.out.synchronized {
    (SimpleReader.readLine("Please enter your PGP passphrase> ") getOrElse error("No password provided.")).toCharArray
  }
  
  // Helper to figure out how to run GPG signing...
  def isWindows = System.getProperty("os.name").toLowerCase.indexOf("windows") != -1
}

object sys {
  def error(s: String) = Predef.error(s)
}
