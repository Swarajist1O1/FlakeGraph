class DummyClass_98594 {
    @Test
    public void callMethod() {
        assertEquals('j', El.eval("'jk'.charAt(0)"));
        assertEquals("cde", El.eval("\"abcde\".substring(2)"));
        assertEquals("b", El.eval("\"abcde\".substring(1,2)"));
        assertEquals(true, El.eval("\"abcd\".regionMatches(2,\"ccd\",1,2)"));
        assertEquals("bbbb", El.eval("'  abab  '.replace('a','b').trim()"));
    }

}