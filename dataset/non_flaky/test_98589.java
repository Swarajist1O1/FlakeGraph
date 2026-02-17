class DummyClass_98589 {
    @Test
    public void logical() {
        assertEquals(true, El.eval("2 > 1"));
        assertEquals(false, El.eval("2 < 1"));
        assertEquals(true, El.eval("2 >= 2"));
        assertEquals(true, El.eval("2 <= 2"));
        assertEquals(true, El.eval("2 == 2 "));
        assertEquals(true, El.eval("1 != 2"));
        assertEquals(true, El.eval("!(1 == 2)"));
        assertEquals(true, El.eval("!false"));
        assertEquals(true, El.eval("true || false"));
        assertEquals(false, El.eval("true && false"));
        assertEquals(false, El.eval("false || true && false"));
    }

}