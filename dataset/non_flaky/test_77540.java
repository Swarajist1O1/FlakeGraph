class DummyClass_77540 {
    @Test
    public void testDefaultEofExceptionMapper() {
        assertThat(RESOURCES.target("/person/blah/eof-exception")
            .request()
            .get().readEntity(String.class))
            .isEqualTo("Something went wrong: I'm an eof exception!");
    }

}