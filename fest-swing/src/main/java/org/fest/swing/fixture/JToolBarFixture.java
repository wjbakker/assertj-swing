/*
 * Created on Jul 5, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.*;
import java.util.regex.Pattern;

import javax.swing.JToolBar;

import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.JToolBarDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of {@code JToolBar}s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class JToolBarFixture extends AbstractContainerFixture<JToolBar> implements CommonComponentFixture,
  AbstractJComponentFixture, JPopupMenuInvokerFixture {
  /**
   * Understands constraints used to unfloat a floating {@code JToolBar}.
   *
   * @author Alex Ruiz
   */
  public enum UnfloatConstraint {
    NORTH(BorderLayout.NORTH), EAST(BorderLayout.EAST), SOUTH(BorderLayout.SOUTH), WEST(BorderLayout.WEST);

    public final String value;

    UnfloatConstraint(String value) {
      this.value = value;
    }
  }

  private JToolBarDriver driver;

  /**
   * Creates a new {@link JToolBarFixture}.
   * @param robot performs simulation of user events on the given {@code JToolBar}.
   * @param target the {@code JToolBar} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public JToolBarFixture(Robot robot, JToolBar target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new {@link JToolBarFixture}.
   * @param robot performs simulation of user events on a {@code JToolBar}.
   * @param toolbarName the name of the {@code JToolBar} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a matching {@code JToolBar} could not be found.
   * @throws ComponentLookupException if more than one matching {@code JToolBar} is found.
   */
  public JToolBarFixture(Robot robot, String toolbarName) {
    super(robot, toolbarName, JToolBar.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JToolBarDriver(robot));
  }

  /**
   * Sets the {@link JToolBarDriver} to be used by this fixture.
   * @param newDriver the new {@code JToolBarDriver}.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JToolBarDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user dragging this fixture's {@code JToolBar} to the given location, causing it to float.
   * @param point the point where the {@code JToolBar} will be floating to.
   * @return this fixture.
   * @throws ActionFailedException if the {@code JToolBar} is not floatable.
   * @throws ActionFailedException if the {@code JToolBar} cannot be dragged.
   */
  public JToolBarFixture floatTo(Point point) {
    driver.floatTo(target, point.x, point.y);
    return this;
  }

  /**
   * Simulates a user unfloating this fixture's {@code JToolBar}.
   * @return this fixture.
   * @throws ActionFailedException if the dock container cannot be found.
   */
  public JToolBarFixture unfloat() {
    driver.unfloat(target);
    return this;
  }

  /**
   * Simulates a user dropping this fixture's  {@code JToolBar} to the requested constraint position.
   * @param constraint the constraint position.
   * @return this fixture.
   * @throws ActionFailedException if the dock container cannot be found.
   */
  public JToolBarFixture unfloat(UnfloatConstraint constraint) {
    driver.unfloat(target, constraint.value);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JToolBar}.
   * @return this fixture.
   */
  public JToolBarFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JToolBar}.
   * @param button the button to click.
   * @return this fixture.
   */
  public JToolBarFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JToolBar}.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   */
  public JToolBarFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's {@code JToolBar}.
   * @return this fixture.
   */
  public JToolBarFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's {@code JToolBar}.
   * @return this fixture.
   */
  public JToolBarFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's {@code JToolBar}.
   * @return this fixture.
   */
  public JToolBarFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's {@code JToolBar}.
   * Modifiers is a mask from the available {@link java.awt.event.InputEvent} masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given {@code KeyPressInfo} is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public JToolBarFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys in this fixture's {@code JToolBar}. This method
   * does not affect the current focus.
   * @param keyCodes the codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JToolBarFixture pressAndReleaseKeys(int...keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's {@code JToolBar}.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JToolBarFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's {@code JToolBar}.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JToolBarFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JToolBar} has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JToolBar} does not have input focus.
   */
  public JToolBarFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JToolBar} is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JToolBar} is disabled.
   */
  public JToolBarFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JToolBar} is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's {@code JToolBar} is never enabled.
   */
  public JToolBarFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JToolBar} is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JToolBar} is enabled.
   */
  public JToolBarFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JToolBar} is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JToolBar} is not visible.
   */
  public JToolBarFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JToolBar} is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JToolBar} is visible.
   */
  public JToolBarFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }


  /**
   * Asserts that the toolTip in this fixture's {@code JToolBar} matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's {@code JToolBar} does not match the given value.
   * @since 1.2
   */
  public JToolBarFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@code JToolBar} matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's {@code JToolBar} does not match the given regular
   * expression.
   * @since 1.2
   */
  public JToolBarFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's {@link JToolBarFixture}, under the given key.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or {@code null} if the property was
   * not found.
   * @throws NullPointerException if the given key is {@code null}.
   * @since 1.2
   */
  public Object clientProperty(Object key) {
    return driver.clientProperty(target, key);
  }

  /**
   * Shows a pop-up menu using this fixture's {@code JToolBar} as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JToolBar} is disabled.
   * @throws IllegalStateException if this fixture's {@code JToolBar} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's {@code JToolBar} as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JToolBar} is disabled.
   * @throws IllegalStateException if this fixture's {@code JToolBar} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
