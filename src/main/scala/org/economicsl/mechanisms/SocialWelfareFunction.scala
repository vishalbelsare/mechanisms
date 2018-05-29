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

import scala.collection.GenSet


/** Base trait defining a generic social welfare function.
  *
  * A social welfare function aggregates the preferences of individual agents
  * into a common preference ordering.
  */
trait SocialWelfareFunction[-P <: Preference[_ <: Alternative]] {

  def apply[P1 <: P](preferences: GenSet[P1]): P1

}
