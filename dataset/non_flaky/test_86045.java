class DummyClass_86045 {
    @Test
    public void testValidEmailNotification() {
        final NotificationDto validNotification = getEmailNotification();

        final ValidationResult validationResult = validNotification.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}