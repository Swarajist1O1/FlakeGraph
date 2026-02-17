class DummyClass_98599 {
    @Test
    public void custom() {
        assertEquals(2, El.eval("max(1, 2)"));
        assertEquals(1, El.eval("min(1, 2)"));
        assertEquals("jk", El.eval("trim('    jk    ')"));
    }

}