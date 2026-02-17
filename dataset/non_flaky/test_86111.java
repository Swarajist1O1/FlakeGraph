class DummyClass_86111 {
    @Test
    public void loadNativeEntity() {
        final NativeEntityDescriptor nativeEntityDescriptor = NativeEntityDescriptor.create(
                ModelId.of("content-pack-id"),
                ModelId.of("5d4d33753d27460ad18e0c4d"),
                ModelTypes.NOTIFICATION_V1,
                "title");
        final Optional<NativeEntity<NotificationDto>> optionalNativeEntity = facade.loadNativeEntity(
                nativeEntityDescriptor);
        assertThat(optionalNativeEntity).isPresent();
        final NativeEntity<NotificationDto> nativeEntity = optionalNativeEntity.get();
        assertThat(nativeEntity.entity()).isNotNull();
        final NotificationDto notificationDto = nativeEntity.entity();
        assertThat(notificationDto.id()).isEqualTo("5d4d33753d27460ad18e0c4d");
    }

}