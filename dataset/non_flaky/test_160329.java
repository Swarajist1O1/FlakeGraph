class DummyClass_160329 {
    @Test
    public void shouldReturnDefaultValueWhenKeyIsAbsent() throws Exception {
      final boolean defaultValue = true;
      final PropertyReader propertyReader = newEmptyPropertyReader();

      assertThat(
          propertyReader.readBooleanPropertyOrDefault(ABSENT_PROPERTY_KEY, defaultValue),
          is(defaultValue));
    }

}