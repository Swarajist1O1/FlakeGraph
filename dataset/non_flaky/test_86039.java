class DummyClass_86039 {
    @Test
    public void testValidateWithEmptyTitle() {
        final NotificationDto invalidNotification = getHttpNotification().toBuilder().title("").build();
        final ValidationResult validationResult = invalidNotification.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("title");
    }

}