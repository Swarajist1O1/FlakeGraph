class DummyClass_86040 {
    @Test
    public void testValidateWithEmptyConfig() {
        final NotificationDto invalidNotification = NotificationDto.builder()
                .title("Foo")
                .description("")
                .config(new EventNotificationConfig.FallbackNotificationConfig())
                .build();
        final ValidationResult validationResult = invalidNotification.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("config");
    }

}