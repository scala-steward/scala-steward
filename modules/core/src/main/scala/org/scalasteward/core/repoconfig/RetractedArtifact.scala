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

package org.scalasteward.core.repoconfig

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import org.scalasteward.core.data.Update

final case class RetractedArtifact(
    reason: String,
    doc: String,
    artifacts: List[UpdatePattern] = List.empty
) {
  def isRetracted(updateSingle: Update.Single): Boolean =
    updateSingle.forArtifactIds.exists { updateForArtifactId =>
      UpdatePattern
        .findMatch(artifacts, updateForArtifactId, include = true)
        .filteredVersions
        .nonEmpty
    }

  def retractionMsg: String =
    s"""|Retracted because of: ${reason}.
        |
        |Documentation: ${doc}
        |""".stripMargin.trim
}

object RetractedArtifact {
  implicit val retractedPatternCodec: Codec[RetractedArtifact] =
    deriveCodec
}
