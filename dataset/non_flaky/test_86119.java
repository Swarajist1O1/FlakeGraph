class DummyClass_86119 {
    @Test
    public void createExcerpt() {
        final Optional<EventDefinitionDto> eventDefinitionDto = eventDefinitionService.get(
                "5d4032513d2746703d1467f6");
        assertThat(eventDefinitionDto).isPresent();
        final EntityExcerpt excerpt = facade.createExcerpt(eventDefinitionDto.get());
        assertThat(excerpt.title()).isEqualTo("title");
        assertThat(excerpt.id()).isEqualTo(ModelId.of("5d4032513d2746703d1467f6"));
        assertThat(excerpt.type()).isEqualTo(ModelTypes.EVENT_DEFINITION_V1);
    }

}