class DummyClass_98588 {
    @Test
    public void testNull() {
        assertEquals(null, El.eval("null"));
        assertTrue((Boolean) El.eval("null == null"));
    }

}