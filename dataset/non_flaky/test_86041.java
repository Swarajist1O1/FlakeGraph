class DummyClass_86041 {
    @Test
    public void testValidateHttpWithEmptyConfigParameters() {
        final HTTPEventNotificationConfig emptyConfig = HTTPEventNotificationConfig.Builder.create()
                .url("")
                .build();
        final NotificationDto emptyNotification = getHttpNotification().toBuilder().config(emptyConfig).build();
        final ValidationResult validationResult = emptyNotification.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("url");
    }

}