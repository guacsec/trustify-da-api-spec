/*
 * Copyright 2023-2025 Trustify Dependency Analytics Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.guacsec.trustifyda.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PackageRefTest {

  @Test
  public void testNamespace() {
    var ref = new PackageRef("pkg:golang/google.golang.org/genproto#googleapis/api/annotations");
    assertEquals("google.golang.org/genproto", ref.name());

    ref = new PackageRef("pkg:golang/go.opencensus.io@v0.21.0");
    assertEquals("go.opencensus.io", ref.name());

    ref = new PackageRef("pkg:npm/foobar@12.3.1");
    assertEquals("foobar", ref.name());

    ref = new PackageRef("pkg:npm/%40babel/helper-compilation-targets@7.17.7");
    assertEquals("@babel/helper-compilation-targets", ref.name());

    ref = new PackageRef("pkg:maven/org.apache.xmlgraphics/batik-anim@1.9.1?packaging=sources");
    assertEquals("org.apache.xmlgraphics:batik-anim", ref.name());
  }

  @Test
  public void testVersion() {
    var ref = new PackageRef("pkg:golang/google.golang.org/genproto#googleapis/api/annotations");
    assertNull(ref.version());

    ref = new PackageRef("pkg:golang/go.opencensus.io@v0.21.0");
    assertEquals("v0.21.0", ref.version());
  }

  @Test
  public void testCoordinatesEquals() {
    var originalPurl = "pkg:maven/jakarta.validation/jakarta.validation-api@2.0.2.redhat-00005?repository_url=https%3A%2F%2Fmaven.repository.redhat.com%2Fga%2F&type=jar";
    var coordinates = "pkg:maven/jakarta.validation/jakarta.validation-api@2.0.2.redhat-00005";
    var ref = new PackageRef(originalPurl);
    assertEquals(originalPurl, ref.toString());

    assertEquals(coordinates, ref.purl().getCoordinates());

    assertEquals(originalPurl, ref.toString());

    assertTrue(ref.isCoordinatesEquals(new PackageRef(coordinates)));
  }
}
