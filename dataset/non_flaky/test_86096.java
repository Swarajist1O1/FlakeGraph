class DummyClass_86096 {
    @Test
    public void testValidateWithKeySpecNotInFieldSpec() {
        final EventFieldSpec fieldSpecMock = mock(EventFieldSpec.class);
        final EventDefinitionDto invalidEventDefinition = testSubject.toBuilder()
            .fieldSpec(ImmutableMap.of("bar", fieldSpecMock, "baz", fieldSpecMock))
            .keySpec(ImmutableList.of("foo"))
            .build();
        final ValidationResult validationResult = invalidEventDefinition.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("key_spec");
    }

}