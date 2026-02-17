class DummyClass_86095 {
    @Test
    public void testValidateWithInvalidFieldName() {
        final EventFieldSpec fieldSpecMock = mock(EventFieldSpec.class);
        final EventDefinitionDto invalidEventDefinition = testSubject.toBuilder()
            .fieldSpec(ImmutableMap.of("foo\\bar", fieldSpecMock, "$yo&^a", fieldSpecMock))
            .build();
        final ValidationResult validationResult = invalidEventDefinition.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("field_spec");
        final List<String> fieldValidation = (List<String>) validationResult.getErrors().get("field_spec");
        assertThat(fieldValidation.size()).isEqualTo(2);
        assertThat(fieldValidation.get(0)).contains("foo\\bar");
        assertThat(fieldValidation.get(1)).contains("$yo&^a");
    }

}