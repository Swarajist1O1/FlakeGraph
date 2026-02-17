class DummyClass_86115 {
    @Test
    public void exportEntity() {
        final ModelId id = ModelId.of("5d4032513d2746703d1467f6");

        when(jobDefinitionService.getByConfigField(eq("event_definition_id"), eq(id.id())))
                .thenReturn(Optional.of(mock(JobDefinitionDto.class)));

        final EntityDescriptor descriptor = EntityDescriptor.create(id, ModelTypes.EVENT_DEFINITION_V1);
        final EntityDescriptorIds entityDescriptorIds = EntityDescriptorIds.of(descriptor);
        final Optional<Entity> entity = facade.exportEntity(descriptor, entityDescriptorIds);
        assertThat(entity).isPresent();
        final EntityV1 entityV1 = (EntityV1) entity.get();
        final EventDefinitionEntity eventDefinitionEntity = objectMapper.convertValue(entityV1.data(),
                EventDefinitionEntity.class);
        assertThat(eventDefinitionEntity.title().asString()).isEqualTo("title");
        assertThat(eventDefinitionEntity.description().asString()).isEqualTo("description");
        assertThat(eventDefinitionEntity.config().type()).isEqualTo(AggregationEventProcessorConfigEntity.TYPE_NAME);
        assertThat(eventDefinitionEntity.isScheduled().asBoolean(ImmutableMap.of())).isTrue();
    }

}