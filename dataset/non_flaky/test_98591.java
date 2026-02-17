class DummyClass_98591 {
    @Test
    public void stringTest() {
        assertEquals("jk", El.eval("'jk'"));
        assertEquals(2, El.eval("'jk'.length()"));
        assertEquals(2, El.eval("\"jk\".length()"));
        assertEquals("jk", El.eval("\"    jk   \".trim()"));
        assertEquals("j\\n\\tk", El.eval("\"j\\n\\tk\""));
    }

}