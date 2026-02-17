class DummyClass_98593 {
    @Test
    public void negative() {
        assertEquals(-1, El.eval("-1"));
        assertEquals(0, El.eval("-1+1"));
        assertEquals(-1 - -1, El.eval("-1 - -1"));
        assertEquals(9 + 8 * 7 + (6 + 5) * (-(4 - 1 * 2 + 3)), El.eval("9+8*7+(6+5)*(-(4-1*2+3))"));
    }

}