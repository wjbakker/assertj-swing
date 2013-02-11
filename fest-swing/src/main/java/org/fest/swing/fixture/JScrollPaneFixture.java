/*
 * Created on Dec 25, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.*;

import org.fest.swing.core.*;
import org.fest.swing.driver.JScrollPaneDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of {@code JScrollPane}s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JScrollPaneFixture extends AbstractComponentFixture<JScrollPane> implements CommonComponentFixture,
    JPopupMenuInvokerFixture, AbstractJComponentFixture {
  private JScrollPaneDriver driver;

  /**
   * Creates a new {@link JScrollPaneFixture}.
   * @param robot performs simulation of user events on the given {@code JScrollPane}.
   * @param target the {@code JScrollPane} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws IllegalArgumentException if {@code target} is {@code null}.
   */
  public JScrollPaneFixture(Robot robot, JScrollPane target) {
    super(robot, target);
    driver(newComponentDriver());
  }

  /**
   * Sets the {@link JScrollPaneDriver} to be used by this fixture.
   * @param newDriver the new {@code JScrollPaneDriver}.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JScrollPaneDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Creates a new {@link JScrollPaneFixture}.
   * @param robot performs simulation of user events on a {@code JScrollPane}.
   * @param panelName the name of the {@code JScrollPane} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a matching {@code JScrollPane} could not be found.
   * @throws ComponentLookupException if more than one matching {@code JScrollPane} is found.
   */
  public JScrollPaneFixture(Robot robot, String panelName) {
    super(robot, panelName, JScrollPane.class);
    driver = newComponentDriver();
  }

  private JScrollPaneDriver newComponentDriver() {
    return new JScrollPaneDriver(robot);
  }

  /**
   * Returns a {@link JScrollBarFixture} managing the horizontal {@code JScrollBar} of this
   * target's {@code JScrollPane}.
   * @return a fixture managing the horizontal {@code JScrollBar} of this target's {@code JScrollPane}.
   */
  public JScrollBarFixture horizontalScrollBar() {
    return scrollBarFixture(driver.horizontalScrollBarIn(target));
  }

  /**
   * Returns a {@link JScrollBarFixture} managing the vertical {@code JScrollBar} of this
   * target's {@code JScrollPane}.
   * @return a fixture managing the vertical {@code JScrollBar} of this target's {@code JScrollPane}.
   */
  public JScrollBarFixture verticalScrollBar() {
    return scrollBarFixture(driver.verticalScrollBarIn(target));
  }

  private JScrollBarFixture scrollBarFixture(JScrollBar scrollBar) {
    return new JScrollBarFixture(robot, scrollBar);
  }

  /**
   * Simulates a user clicking this fixture's {@code JScrollPane}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   */
  public JScrollPaneFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JScrollPane}.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseButton} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   */
  public JScrollPaneFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JScrollPane}.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   */
  public JScrollPaneFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's {@code JScrollPane}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   */
  public JScrollPaneFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's {@code JScrollPane}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   */
  public JScrollPaneFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's {@code JScrollPane}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   */
  public JScrollPaneFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's {@code JScrollPane}.
   * Modifiers is a mask from the available {@link java.awt.event.InputEvent} masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given {@code KeyPressInfo} is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   * @see KeyPressInfo
   */
  public JScrollPaneFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on the {@code JScrollPane} managed by this
   * fixture.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JScrollPaneFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's {@code JScrollPane}.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JScrollPaneFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's {@code JScrollPane}.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JScrollPaneFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JScrollPane} has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JScrollPane} does not have input focus.
   */
  public JScrollPaneFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JScrollPane} is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JScrollPane} is disabled.
   */
  public JScrollPaneFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JScrollPane} is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's {@code JScrollPane} is never enabled.
   */
  public JScrollPaneFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JScrollPane} is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JScrollPane} is enabled.
   */
  public JScrollPaneFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JScrollPane} is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JScrollPane} is not visible.
   */
  public JScrollPaneFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JScrollPane} is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JScrollPane} is visible.
   */
  public JScrollPaneFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@code JScrollPane} matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's {@code JScrollPane} does not match the given value.
   * @since 1.2
   */
  public JScrollPaneFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@code JScrollPane} matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's {@code JScrollPane} does not match the given regular
   * expression.
   * @since 1.2
   */
  public JScrollPaneFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's {@code JScrollPane}, under the given key.
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
   * Shows a pop-up menu using this fixture's {@code JScrollPane} as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's {@code JScrollPane} as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JScrollPane} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
