class DummyClass_112075 {
    @Test
    public void assertGetCause() {
        assertThat(new JobStatisticException(new RuntimeException()).getCause(), instanceOf(RuntimeException.class));
    }

}