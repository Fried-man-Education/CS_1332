package java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


import jdk.jfr.StackTrace;

public class MyStackTest {
    private MyStack ourStack;
    @Before
    public void setup() {
        ourStack = new MyStack();
    }

    @Test
    public void myInitialPopTest(){
        ourStack.push("FirstElement");
        assertEquals("FirstElement", ourStack.pop());
    }

    @Test
    public void levelPeekTest() {
        String temp = "item #last";

        for (int i = 0; i < 5; i++) {
            ourStack.push("item #" + i);
        }
        ourStack.push(temp);

        assertSame(temp, ourStack.pop()); // 4, 3, 2, 1, 0

        String cur = null;
        for (int i = 0; i < 5; i++) {
            cur = ourStack.top();
            assertNotNull(cur);
            assertEquals(cur, ourStack.pop());
        }

        assertNull(ourStack.top());
    }
}