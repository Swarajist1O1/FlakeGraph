class DummyClass_98586 {
    @Test
    public void multiStageOperation() {
        assertEquals(3, El.eval("1 + 1 + 1"));
        assertEquals(1, El.eval("1+1-1"));
        assertEquals(-1, El.eval("1-1-1"));
        assertEquals(1, El.eval("1-(1-1)"));
        assertEquals(7, El.eval("1+2*3"));
        assertEquals(2 * 4 + 2 * 3 + 4 * 5, El.eval("2*4+2*3+4*5"));
        assertEquals(9 + 8 * 7 + (6 + 5) * ((4 - 1 * 2 + 3)), El.eval("9+8*7+(6+5)*((4-1*2+3))"));
        assertEquals(.3 + .2 * .5, El.eval(".3+.2*.5"));
        assertEquals((.5 + 0.1) * .9, El.eval("(.5 + 0.1)*.9"));
    }

}