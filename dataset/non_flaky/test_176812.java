class DummyClass_176812 {
    @Test
    public void matchesEndWithRegexp() throws Exception {
        assertThat("hello world", EndsWithRegexp.endsWithRegexp("el.*world"));
    }

}