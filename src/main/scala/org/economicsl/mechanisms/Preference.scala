/*
Copyright 2017-2018 EconomicSL

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.economicsl.mechanisms

import cats._
import cats.implicits._

import scala.collection.immutable.TreeSet


/** Base trait representing an agent's preferences defined over a particular
  * type of `Alternative`.
  * @tparam A
  */
trait Preference[A] extends Order[A] {

  final def mostPreferred(alternatives: Iterable[A]): A = {
    alternatives.reduce(max)
  }

  final def rank(alternatives: Iterable[A]): Map[A, Int] = {
    val sortedAlternatives = TreeSet.empty[A](toOrdering) ++ alternatives
    sortedAlternatives.zipWithIndex.aggregate(Map.empty[A, Int])(_ + _, _ |+| _)
  }

}


object Preference {

  implicit val contravariant: Contravariant[Preference] = {
    new Contravariant[Preference] {
      def contramap[A, B](fa: Preference[A])(f: B => A): Preference[B] = {
        new Preference[B] {
          def compare(b1: B, b2: B): Int = {
            fa.compare(f(b1), f(b2))
          }
        }
      }
    }
  }

  /** Assumes that alternatives are ordered from least to most preferred. */
  def fromSeq[A](alternatives: Seq[A]): Preference[A] = {
    new Preference[A] {
      def compare(a1: A, a2: A): Int = {
        alternatives.indexOf(a1).compare(alternatives.indexOf(a2))
      }
    }
  }

  /** Defines a preference for a particular alternative. */
  def particular[A](alternative: A): Preference[A] = {
    new Preference[A] {
      def compare(a1: A, a2: A): Int = {
        if ((a1 != alternative) & (a2 == alternative)) {
          -1
        } else if ((a1 == alternative) & (a2 != alternative)) {
          1
        } else {
          0
        }
      }
    }
  }

}
