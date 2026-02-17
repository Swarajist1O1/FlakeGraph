class DummyClass_86044 {
    @Test
    public void testValidHttpNotification() {
        final NotificationDto validNotification = getHttpNotification();

        final ValidationResult validationResult = validNotification.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}