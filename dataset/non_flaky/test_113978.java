class DummyClass_113978 {
    @Test
    public void testIsLiteral() {
        NamedVariablePatternMatcher matcher = new NamedVariablePatternMatcher();

        assertTrue(matcher.isLiteral("bob"));
        assertFalse(matcher.isLiteral("bob{jim}"));
    }

}