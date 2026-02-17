class DummyClass_113976 {
    @Test(expected = IllegalArgumentException.class)
    public void testCompileWithMismatchedBracketsParses() {
        NamedVariablePatternMatcher matcher = new NamedVariablePatternMatcher();

        matcher.compilePattern("}");

}