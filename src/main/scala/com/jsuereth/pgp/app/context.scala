package com.jsuereth.pgp
package app

import java.io.File
import sbt._

import sbtplugin.Cache

case class ConsolePgpStaticContext(
    publicKeyRingFile: File,
    secretKeyRingFile: File) extends cli.PgpStaticContext {
  
}

/** Context used by PGP commands as they execute. */
case class ConsolePgpCommandContext(
    ctx: cli.PgpStaticContext,
    log: sbt.Logger
  ) extends cli.PgpCommandContext with cli.DelegatingPgpStaticContext {
  
  def readInput(msg: String): String = System.out.synchronized {
    SimpleReader.readLine(msg) getOrElse sys.error("Failed to grab input")
  }
  def readHidden(msg: String): String = System.out.synchronized {
    SimpleReader.readLine(msg, Some('*')) getOrElse sys.error("Failed to grab input")
  }
  def inputPassphrase = readHidden("Please enter PGP passphrase: ").toCharArray
  def withPassphrase[U](f: Array[Char] => U): U =
    PasswordCache.withValue(
        key = ctx.secretKeyRingFile.getAbsolutePath,
        default = inputPassphrase)(f)
  
  // TODO - Is this the right thing to do?
  def output[A](msg: => A): Unit = println(msg)
}

private[app] object PasswordCache extends Cache[String, Array[Char]]