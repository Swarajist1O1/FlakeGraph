class DummyClass_160332 {
    @Test
    public void shouldReturnDefaultValueWhenKeyIsAbsent() throws Exception {
      final int defaultValue = 777;
      final PropertyReader propertyReader = newEmptyPropertyReader();

      assertThat(
          propertyReader.readIntegerPropertyOrDefault(ABSENT_PROPERTY_KEY, defaultValue),
          is(defaultValue));
    }

}