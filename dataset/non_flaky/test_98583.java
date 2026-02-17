class DummyClass_98583 {
    @Test
    public void notCalculateOneNumber() {
        assertEquals(1, El.eval("1"));
        assertEquals(0.1, El.eval(".1"));
        assertEquals(0.1d, El.eval("0.1"));
        assertEquals(0.1f, El.eval("0.1f"));
        assertEquals(0.1d, El.eval("0.1d"));
        assertEquals(true, El.eval("true"));
        assertEquals(false, El.eval("false"));
        assertEquals("jk", El.eval("'jk'"));
    }

}