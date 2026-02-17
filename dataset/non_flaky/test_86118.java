class DummyClass_86118 {
    @Test
    public void loadNativeEntity() {
        final NativeEntityDescriptor nativeEntityDescriptor = NativeEntityDescriptor
                .create(ModelId.of("content-pack-id"),
                        ModelId.of("5d4032513d2746703d1467f6"),
                        ModelTypes.EVENT_DEFINITION_V1,
                        "title");
        final Optional<NativeEntity<EventDefinitionDto>> optionalNativeEntity = facade.loadNativeEntity(nativeEntityDescriptor);
        assertThat(optionalNativeEntity).isPresent();
        final NativeEntity<EventDefinitionDto> nativeEntity = optionalNativeEntity.get();
        assertThat(nativeEntity.entity()).isNotNull();
        final EventDefinitionDto eventDefinition = nativeEntity.entity();
        assertThat(eventDefinition.id()).isEqualTo("5d4032513d2746703d1467f6");
    }

}