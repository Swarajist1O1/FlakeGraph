class DummyClass_176814 {
    @Test
    public void matchesNotEndWithRegexp() throws Exception {
        assertThat("hello world", Matchers.not(EndsWithRegexp.endsWithRegexp("h.*o")));
    }

}