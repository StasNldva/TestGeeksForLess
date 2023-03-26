package org.example.neledva;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquationCheckerGUITest {
    private EquationCheckerGUI equationCheckerGUI;

    @Before
    public void setUp(){
        equationCheckerGUI = new EquationCheckerGUI();
    }

    @Test
    public void countNum() {
        assertEquals(1, equationCheckerGUI.countNum("12354"));

    }

    @Test
    public void countNumIfStringIsEmpty(){
        assertEquals(0, equationCheckerGUI.countNum(""));
    }

    @Test
    public void countNumIfHasSomeCharacter(){
        assertEquals(2, equationCheckerGUI.countNum("12354x+45"));
    }

    @Test
    public void countNumStringLine(){
        assertEquals(0, equationCheckerGUI.countNum("nothing"));
    }

    @Test
    public void checkEquation() {
        assertSame(true, equationCheckerGUI.checkEquation("1=1"));
    }

    @Test
    public void checkEquationIfNotSame() {
        assertFalse(String.valueOf(equationCheckerGUI.checkEquation("1/=1")), false);
    }


    @Test
    public void checkParentheses() {
        assertTrue(equationCheckerGUI.checkParentheses("(())()"));
        assertTrue(equationCheckerGUI.checkParentheses("()"));
        assertTrue(equationCheckerGUI.checkParentheses("(()())"));
    }

    @Test
    public void checkParenthesesDiff() {
        assertFalse(equationCheckerGUI.checkParentheses("(()()"));
        assertFalse(equationCheckerGUI.checkParentheses("("));
        assertFalse(equationCheckerGUI.checkParentheses("((())"));
    }
}