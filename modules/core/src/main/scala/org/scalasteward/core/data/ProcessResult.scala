/*
 * Copyright 2018-2023 Scala Steward contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scalasteward.core.data

import org.scalasteward.core.vcs.data.PullRequestNumber

sealed trait ProcessResult extends Product with Serializable

object ProcessResult {
  case object Ignored extends ProcessResult
  case object Updated extends ProcessResult
  case class Created(prNumber: PullRequestNumber) extends ProcessResult
}
