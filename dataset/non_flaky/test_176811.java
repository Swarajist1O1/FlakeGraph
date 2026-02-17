class DummyClass_176811 {
    @Test
    public void matchesEndWith() throws Exception {
        assertThat("hello world", EndsWithRegexp.endsWithRegexp("world"));
    }

}