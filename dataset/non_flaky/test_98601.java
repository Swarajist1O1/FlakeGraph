class DummyClass_98601 {
    @Test
    public void lssue_486() {
        assertEquals(2 + (-3), El.eval("2+(-3)"));
        assertEquals(2 + -3, El.eval("2+-3"));
        assertEquals(2 * -3, El.eval("2*-3"));
        assertEquals(-2 * -3, El.eval("-2*-3"));
        assertEquals(2 / -3, El.eval("2/-3"));
        assertEquals(2 % -3, El.eval("2%-3"));
    }

}