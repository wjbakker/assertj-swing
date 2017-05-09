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
package org.assertj.swing.fixture;

import static java.awt.Color.BLUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.data.Index.atIndex;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.regex.Pattern;

import javax.swing.JTabbedPane;

import org.assertj.swing.core.Robot;
import org.assertj.swing.driver.JTabbedPaneDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link JTabbedPaneFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author William Bakker
 */
public class JTabbedPaneFixture_withMocks_Test {
  private JTabbedPaneFixture fixture;

  @Before
  public void setUp() {
    fixture = new JTabbedPaneFixture(mock(Robot.class), mock(JTabbedPane.class));
    fixture.replaceDriverWith(mock(JTabbedPaneDriver.class));
  }

  @Test
  public void should_Return_Tab_Titles_Using_Driver() {
    String[] titles = { "One", "Two" };
    JTabbedPaneDriver driver = fixture.driver();
    JTabbedPane target = fixture.target();
    when(driver.tabTitles(target)).thenReturn(titles);
    assertThat(fixture.tabTitles()).isSameAs(titles);
    verify(driver).tabTitles(target);
  }

  @Test
  public void should_Call_SelectTab_With_Index_In_Driver_And_Return_Self() {
    assertThat(fixture.selectTab(6)).isSameAs(fixture);
    verify(fixture.driver()).selectTab(fixture.target(), 6);
  }

  @Test
  public void should_Call_SelectTab_With_Text_In_Driver_And_Return_Self() {
    assertThat(fixture.selectTab("Six")).isSameAs(fixture);
    verify(fixture.driver()).selectTab(fixture.target(), "Six");
  }

  @Test
  public void should_Call_SelectTab_With_Pattern_In_Driver_And_Return_Self() {
    Pattern pattern = Pattern.compile("Six");
    assertThat(fixture.selectTab(pattern)).isSameAs(fixture);
    verify(fixture.driver()).selectTab(fixture.target(), pattern);
  }

  @Test
  public void should_Call_RequireTitle_With_Text_In_Driver_And_Return_Self() {
    assertThat(fixture.requireTitle("Hello", atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireTabTitle(fixture.target(), "Hello", atIndex(6));
  }

  @Test
  public void should_Call_RequireTitle_With_Pattern_In_Driver_And_Return_Self() {
    Pattern pattern = Pattern.compile("Hello");
    assertThat(fixture.requireTitle(pattern, atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireTabTitle(fixture.target(), pattern, atIndex(6));
  }

  @Test
  public void should_Call_RequireTabTitles_In_Driver_And_Return_Self() {
    String[] titles = { "One", "Two" };
    assertThat(fixture.requireTabTitles(titles)).isSameAs(fixture);
    verify(fixture.driver()).requireTabTitles(fixture.target(), titles);
  }

  @Test
  public void should_Call_RequireSelectedTab_In_Driver_And_Return_Self() {
    assertThat(fixture.requireSelectedTab(atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireSelectedTab(fixture.target(), atIndex(6));
  }

  @Test
  public void should_Call_RequireToolTipText_With_Text_In_Driver_And_Return_Self() {
    assertThat(fixture.requireToolTipText("Hello", atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireTabToolTipText(fixture.target(), "Hello", atIndex(6));
  }

  @Test
  public void should_Call_RequireToolTipText_With_Pattern_In_Driver_And_Return_Self() {
    Pattern pattern = Pattern.compile("Hello");
    assertThat(fixture.requireToolTipText(pattern, atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireTabToolTipText(fixture.target(), pattern, atIndex(6));
  }

  @Test
  public void should_Call_RequireEnabled_In_Driver_And_Return_Self() {
    assertThat(fixture.requireEnabled(atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireTabEnabled(fixture.target(), atIndex(6));
  }

  @Test
  public void should_Call_RequireDisabled_In_Driver_And_Return_Self() {
    assertThat(fixture.requireDisabled(atIndex(6))).isSameAs(fixture);
    verify(fixture.driver()).requireTabDisabled(fixture.target(), atIndex(6));
  }

  @Test
  public void should_Return_Tab_Background_Color_Using_Driver() {
    when(fixture.driver().backgroundAt(fixture.target(), atIndex(6))).thenReturn(BLUE);
    ColorFixture colorFixture = fixture.backgroundAt(atIndex(6));
    assertThat(colorFixture.target()).isSameAs(BLUE);
    verify(fixture.driver()).backgroundAt(fixture.target(), atIndex(6));
  }

  @Test
  public void should_Return_Tab_Foreground_Color_Using_Driver() {
    when(fixture.driver().foregroundAt(fixture.target(), atIndex(6))).thenReturn(BLUE);
    ColorFixture colorFixture = fixture.foregroundAt(atIndex(6));
    assertThat(colorFixture.target()).isSameAs(BLUE);
    verify(fixture.driver()).foregroundAt(fixture.target(), atIndex(6));
  }
}
