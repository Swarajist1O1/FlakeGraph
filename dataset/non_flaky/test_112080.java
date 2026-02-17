class DummyClass_112080 {
    @Test
    public void assertGetMessage() {
        assertThat(new JobSystemException("message is: '%s'", "test").getMessage(), is("message is: 'test'"));
    }

}