class DummyClass_98579 {
    @Test
    public void simpleRPN() throws IOException{
        assertEquals("11+1+", parseRPN("1+1+1"));
        assertEquals("11-", parseRPN("1-1"));
        assertEquals("11-1-", parseRPN("1-1-1"));
        assertEquals("52%1+",parseRPN("5%2+1"));
        assertEquals("152%+",parseRPN("1+5%2"));
    }

}