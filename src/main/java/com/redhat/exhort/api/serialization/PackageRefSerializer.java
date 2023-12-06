/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
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

package com.redhat.exhort.api.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.packageurl.PackageURL;
import com.redhat.exhort.api.PackageRef;

import java.io.IOException;

public class PackageRefSerializer extends StdSerializer<PackageRef> {

  public PackageRefSerializer() {
    this(null);
  }

  public PackageRefSerializer(Class<PackageRef> c) {
    super(c);
  }

  @Override
  public void serialize(PackageRef value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeString(value.purl().toString());
  }
}
