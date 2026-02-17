class DummyClass_98580 {
    @Test
    public void mulRPn() throws IOException{
        assertEquals("512+4*+3-", parseRPN("5+((1+2)*4)-3"));
        assertEquals("987*+65+412*-3+-*+", parseRPN("9+8*7+(6+5)*(-(4-1*2+3))"));
    }

}