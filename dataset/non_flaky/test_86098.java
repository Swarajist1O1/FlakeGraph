class DummyClass_86098 {
    @Test
    public void testValidEventDefinitionWithKeySpecInFieldSpec() {
        final EventFieldSpec fieldSpecMock = mock(EventFieldSpec.class);
        final EventDefinitionDto invalidEventDefinition = testSubject.toBuilder()
            .fieldSpec(ImmutableMap.of("foo", fieldSpecMock, "bar", fieldSpecMock))
            .keySpec(ImmutableList.of("foo", "bar"))
            .build();
        final ValidationResult validationResult = invalidEventDefinition.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}