class DummyClass_86109 {
    @Test
    public void exportEntity() {
        final ModelId id = ModelId.of("5d4d33753d27460ad18e0c4d");
        final EntityDescriptor descriptor = EntityDescriptor.create(id, ModelTypes.NOTIFICATION_V1);
        final EntityDescriptorIds entityDescriptorIds = EntityDescriptorIds.of(descriptor);
        final Optional<Entity> entity = facade.exportEntity(descriptor, entityDescriptorIds);
        assertThat(entity).isPresent();
        final EntityV1 entityV1 = (EntityV1) entity.get();
        final NotificationEntity notificationEntity = objectMapper.convertValue(entityV1.data(),
                NotificationEntity.class);
        assertThat(notificationEntity.title().asString()).isEqualTo("title");
        assertThat(notificationEntity.description().asString()).isEqualTo("description");
        assertThat(notificationEntity.config().type()).isEqualTo("email-notification-v1");
    }

}