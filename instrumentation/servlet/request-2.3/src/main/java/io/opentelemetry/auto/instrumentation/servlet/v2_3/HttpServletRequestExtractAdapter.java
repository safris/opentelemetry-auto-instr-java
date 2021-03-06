/*
 * Copyright 2020, OpenTelemetry Authors
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
package io.opentelemetry.auto.instrumentation.servlet.v2_3;

import io.opentelemetry.context.propagation.HttpTextFormat;
import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestExtractAdapter implements HttpTextFormat.Getter<HttpServletRequest> {

  public static final HttpServletRequestExtractAdapter GETTER =
      new HttpServletRequestExtractAdapter();

  @Override
  public String get(final HttpServletRequest carrier, final String key) {
    /*
     * Read from the attributes and override the headers.
     * This is used by HttpServletRequestSetter when a request is async-dispatched.
     */
    final Object attribute = carrier.getAttribute(key);
    if (attribute instanceof String) {
      return (String) attribute;
    }
    return carrier.getHeader(key);
  }
}
