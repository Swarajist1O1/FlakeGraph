class DummyClass_77546 {
    @Test
    public void testDefaultEofExceptionMapper() {
        assertThat(resourceTestRule.target("/person/blah/eof-exception")
                .request()
                .get().getStatus())
                .isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }

}