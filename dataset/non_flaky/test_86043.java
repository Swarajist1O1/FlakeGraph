class DummyClass_86043 {
    @Test
    public void testValidateLegacyWithEmptyConfigParameters() {
        final LegacyAlarmCallbackEventNotificationConfig emptyConfig = LegacyAlarmCallbackEventNotificationConfig.Builder.create()
                .callbackType("")
                .configuration(new HashMap<>())
                .build();
        final NotificationDto emptyNotification = getLegacyNotification().toBuilder().config(emptyConfig).build();
        final ValidationResult validationResult = emptyNotification.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("callback_type");
    }

}