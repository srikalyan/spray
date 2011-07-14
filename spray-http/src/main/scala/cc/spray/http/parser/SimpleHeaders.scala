/*
 * Copyright (C) 2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.spray.http
package parser

import org.parboiled.scala._
import BasicRules._

/**
 * parser rules for all headers that can be parsed with one simple rule
 */
private[parser] trait SimpleHeaders {
  this: Parser with ProtocolParameterRules with AdditionalRules =>

  def CONTENT_LENGTH = rule {
    oneOrMore(Digit) ~> (s => HttpHeaders.`Content-Length`(s.toInt)) ~ EOI
  }
  
  def DATE = rule {
    HttpDate ~~> HttpHeaders.Date ~ EOI
  }
  
  def X_FORWARDED_FOR = rule {
    oneOrMore(Ip, separator = ListSep) ~ EOI ~~> (HttpHeaders.`X-Forwarded-For`(_))
  }
  
}