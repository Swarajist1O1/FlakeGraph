class DummyClass_176813 {
    @Test
    public void matchesNotEndWith() throws Exception {
        assertThat("hello world", Matchers.not(EndsWithRegexp.endsWithRegexp("hello")));
    }

}