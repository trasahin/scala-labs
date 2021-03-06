package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._
import language.implicitConversions
import language.higherKinds
import scala.util.parsing.json.JSONObject
import scala.util.control._

object ImplicitConversionExercises02 {
  case class Euro(val euros: Int, val cents: Int)

  object Euro {
    def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)
  }

  /**
   * =======================================================
   */
  object Exercise01 {

    class EuroBuilder(val amount: Int, val inCents: Int) {
      def euros = new EuroBuilder(0, inCents + amount * 100)
      def cents = new EuroBuilder(0, inCents + amount)
      def apply(amount: Int) = new EuroBuilder(amount, inCents)
    }

    implicit def fromEuroBuilder(eb: EuroBuilder): Euro = Euro.fromCents(eb.inCents)
    implicit def fromInt(value: Int): EuroBuilder = new EuroBuilder(value, 0)
  }

}
