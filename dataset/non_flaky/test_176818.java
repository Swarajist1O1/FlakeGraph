class DummyClass_176818 {
    @Test
    public void formatShouldEscapeDoubleQuotes() throws Exception {
        WebCustomRequest request = new WebCustomRequest() {{
            setBody("hello \"world\"");
        }};
        assertThat(request.format(), containsString("hello \\\"world\\\""));
    }

}