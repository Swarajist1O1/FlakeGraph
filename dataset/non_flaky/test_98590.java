class DummyClass_98590 {
    @Test
    public void threeTernary() {
        assertEquals(2, El.eval("1>0?2:3"));
        assertEquals(2, El.eval("1>0&&1<2?2:3"));
    }

}