class DummyClass_86112 {
    @Test
    public void createExcerpt() {
        final Optional<NotificationDto> notificationDto = notificationService.get(
                "5d4d33753d27460ad18e0c4d");
        assertThat(notificationDto).isPresent();
        final EntityExcerpt excerpt = facade.createExcerpt(notificationDto.get());
        assertThat(excerpt.title()).isEqualTo("title");
        assertThat(excerpt.id()).isEqualTo(ModelId.of("5d4d33753d27460ad18e0c4d"));
        assertThat(excerpt.type()).isEqualTo(ModelTypes.NOTIFICATION_V1);
    }

}