class DummyClass_112074 {
    @Test
    public void assertGetCause() {
        assertThat(new JobConfigurationException(new RuntimeException()).getCause(), instanceOf(RuntimeException.class));
    }

}