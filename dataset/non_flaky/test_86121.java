class DummyClass_86121 {
    @Test
    public void delete() {
        long countBefore = eventDefinitionService.streamAll().count();
        assertThat(countBefore).isEqualTo(1);

        final Optional<EventDefinitionDto> eventDefinitionDto = eventDefinitionService.get(
                "5d4032513d2746703d1467f6");
        assertThat(eventDefinitionDto).isPresent();
        facade.delete(eventDefinitionDto.get());

        long countAfter = eventDefinitionService.streamAll().count();
        assertThat(countAfter).isEqualTo(0);
    }

}