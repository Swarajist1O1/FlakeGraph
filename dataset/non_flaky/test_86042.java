class DummyClass_86042 {
    @Test
    public void testValidateEmailWithEmptyConfigParameters() {
        final EmailEventNotificationConfig emptyConfig = EmailEventNotificationConfig.Builder.create()
                .sender("")
                .subject("")
                .bodyTemplate("")
                .build();
        final NotificationDto emptyNotification = getEmailNotification().toBuilder().config(emptyConfig).build();
        final ValidationResult validationResult = emptyNotification.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors().size()).isEqualTo(4);
        assertThat(validationResult.getErrors()).containsOnlyKeys("subject", "sender", "body_template", "recipients");
    }

}