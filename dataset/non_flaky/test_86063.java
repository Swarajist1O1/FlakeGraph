class DummyClass_86063 {
    @Test
    public void testValidConfiguration() {
        final ValidationResult validationResult = getConfig().validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}