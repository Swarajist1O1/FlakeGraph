class DummyClass_98585 {
    @Test
    public void bit() {
        assertEquals(-40, El.eval("-5<<3"));
        assertEquals(-1, El.eval("-5>>3"));
        assertEquals(5, El.eval("5>>>32"));
        assertEquals(-5, El.eval("-5>>>32"));
        assertEquals(1, El.eval("5&3"));
        assertEquals(7, El.eval("5|3"));
        assertEquals(-6, El.eval("~5"));
        assertEquals(6, El.eval("5^3"));
    }

}