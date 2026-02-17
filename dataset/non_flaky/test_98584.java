class DummyClass_98584 {
    @Test
    public void simpleCalculate() {
        // å 
        assertEquals(2, El.eval("1+1"));
        assertEquals(2.2, El.eval("1.1+1.1"));
        // å
        assertEquals(1, El.eval("2-1"));
        // ä¹
        assertEquals(9, El.eval("3*3"));
        assertEquals(0, El.eval("3*0"));
        // é¤
        assertEquals(3, El.eval("9/3"));
        assertEquals(2.2, El.eval("4.4/2"));
        assertEquals(9.9 / 3, El.eval("9.9/3"));
        // åä½
        assertEquals(1, El.eval("5%2"));
        assertEquals(1.0 % 0.1, El.eval("1.0%0.1"));

    }

}