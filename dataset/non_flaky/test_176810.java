class DummyClass_176810 {
    @Test
    public void matchesDirectMatch() throws Exception {
        assertThat("hello", EndsWithRegexp.endsWithRegexp("hello"));
    }

}