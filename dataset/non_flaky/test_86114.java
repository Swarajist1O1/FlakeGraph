class DummyClass_86114 {
    @Test
    public void delete() {
        long countBefore = notificationService.streamAll().count();
        assertThat(countBefore).isEqualTo(1);

        final Optional<NotificationDto> notificationDto = notificationService.get(
                "5d4d33753d27460ad18e0c4d");
        assertThat(notificationDto).isPresent();
        facade.delete(notificationDto.get());

        long countAfter = notificationService.streamAll().count();
        assertThat(countAfter).isEqualTo(0);
    }

}