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

import scala.collection.GenSeq


/** Base trait for representing payments made by a player as a function of the
  * valuation functions of all players.
  */
trait PaymentFunction[-CC <: GenSeq[ValuationFunction[_ <: Alternative]]]
  extends (CC => Numeraire) {

  def apply(valuations: CC): Numeraire

}
