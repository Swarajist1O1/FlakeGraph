class DummyClass_176795 {
    @Test
    public void shouldTransformPatternToValidRegex() throws Exception {
        String regex = capturer.transformToRegex("{(hello)} world");
        assertThat(regex, equalTo("(.*) world"));
    }

}