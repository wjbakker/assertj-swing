/*
 * Created on Jan 25, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.reflect.innerclass;

import static org.fest.reflect.core.Reflection.constructor;
import static org.fest.reflect.core.Reflection.field;
import static org.fest.reflect.core.Reflection.method;
import static org.fest.reflect.util.ExpectedFailures.expectIllegalArgumentException;
import static org.fest.reflect.util.ExpectedFailures.expectNullPointerException;
import static org.fest.reflect.util.ExpectedFailures.expectReflectionError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.fest.test.CodeToTest;

/**
 * Tests for the fluent interface for inner classes.
 * 
 * @author Alex Ruiz
 */
public class StaticInnerClass_Test {

  @Test
  public void should_throw_error_if_static_inner_class_name_is_null() {
    expectNullPointerException("The name of the static inner class to access should not be null").on(new CodeToTest() {
      public void run() {
        StaticInnerClassName.startStaticInnerClassAccess(null);
      }
    });
  }

  @Test
  public void should_throw_error_if_static_inner_class_name_is_empty() {
    expectIllegalArgumentException("The name of the static inner class to access should not be empty").on(
        new CodeToTest() {
          public void run() {
            StaticInnerClassName.startStaticInnerClassAccess("");
          }
        });
  }

  @Test
  public void should_throw_error_if_declaring_class_is_null() {
    expectNullPointerException("The declaring class should not be null").on(new CodeToTest() {
      public void run() {
        StaticInnerClassName.startStaticInnerClassAccess("Hello").in(null);
      }
    });
  }

  @Test
  public void should_see_static_inner_class() {
    Class<?> innerClass = StaticInnerClassName.startStaticInnerClassAccess("PrivateInnerClass").in(OuterClass.class)
        .get();
    assertTrue(innerClass.getName().contains("PrivateInnerClass"));
    // make sure we really got the inner classes by creating a new instance and accessing its fields and methods.
    Object leia = constructor().withParameterTypes(String.class).in(innerClass).newInstance("Leia");
    assertEquals("Leia", field("name").ofType(String.class).in(leia).get());
    assertEquals("Leia", method("name").withReturnType(String.class).in(leia).invoke());
  }

  @Test
  public void should_return_null_if_static_inner_class_does_not_exist() {
    String msg = "The static inner class <SomeInnerClass> cannot be found in org.fest.reflect.innerclass.OuterClass";
    expectReflectionError(msg).on(new CodeToTest() {
      public void run() {
        StaticInnerClassName.startStaticInnerClassAccess("SomeInnerClass").in(OuterClass.class).get();
      }
    });
  }
}
