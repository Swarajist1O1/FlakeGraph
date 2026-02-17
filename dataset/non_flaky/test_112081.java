class DummyClass_112081 {
    @Test
    public void assertGetCause() {
        assertThat(new JobSystemException(new RuntimeException()).getCause(), instanceOf(RuntimeException.class));
    }

}