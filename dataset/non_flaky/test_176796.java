class DummyClass_176796 {
    @Test
    public void shouldTransformMultiplePatternToValidRegex() throws Exception {
        String regex = capturer.transformToRegex("{(hello)} {(world)}");
        assertThat(regex, equalTo("(.*) (.*)"));
    }

}