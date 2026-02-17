class DummyClass_86123 {
    @Test
    public void resolveForInstallation() {
        EntityV1 eventEntityV1 = createTestEntity();

        final NotificationEntity notificationEntity = NotificationEntity.builder()
                .title(ValueReference.of("title"))
                .description(ValueReference.of("description"))
                .config(HttpEventNotificationConfigEntity.builder()
                        .url(ValueReference.of("http://url")).build())
                .build();
        final JsonNode data = objectMapper.convertValue(notificationEntity, JsonNode.class);
        final EntityV1 notificationV1 = EntityV1.builder()
                .data(data)
                .id(ModelId.of("123123"))
                .type(ModelTypes.EVENT_DEFINITION_V1)
                .build();

        final EntityDescriptor entityDescriptor = EntityDescriptor.create("123123", ModelTypes.NOTIFICATION_V1);

        Map<String, ValueReference> parameters = ImmutableMap.of();
        Map<EntityDescriptor, Entity> entities = ImmutableMap.of(entityDescriptor, notificationV1);

        Graph<Entity> graph = facade.resolveForInstallation(eventEntityV1, parameters, entities);
        assertThat(graph).isNotNull();
        Set<Entity> expectedNodes = ImmutableSet.of(eventEntityV1, notificationV1);
        assertThat(graph.nodes()).isEqualTo(expectedNodes);
    }

}