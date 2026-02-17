class DummyClass_86046 {
    @Test
    public void testValidLegacyNotification() {
        final NotificationDto validNotification = getLegacyNotification();

        final ValidationResult validationResult = validNotification.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}