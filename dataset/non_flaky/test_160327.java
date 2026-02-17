class DummyClass_160327 {
    @Test
    public void shouldReturnDefaultValueWhenKeyIsAbsent() throws Exception {
      final String defaultValue = "defaultValue";
      final PropertyReader propertyReader = newEmptyPropertyReader();

      assertThat(
          propertyReader.readPropertyOrDefault(ABSENT_PROPERTY_KEY, defaultValue),
          is(defaultValue));
    }

}