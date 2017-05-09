/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.driver;

import static java.awt.Color.YELLOW;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.data.Index.atIndex;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests for {@link JTabbedPaneDriver#backgroundAt(javax.swing.JTabbedPane, org.assertj.swing.data.Index)}.
 * 
 * @author William Bakker
 */
public class JTabbedPaneDriver_backgroundAt_Test extends JTabbedPaneDriver_TestCase {
  @Test
  public void should_Return_Background_Color() {
    Color result = driver.backgroundAt(tabbedPane, atIndex(1));
    assertThat(result).isSameAs(YELLOW);
  }
}
