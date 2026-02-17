class DummyClass_160331 {
    @Test
    public void shouldReturnDefaultValueWhenKeyIsPresentAndValueIsNotAnInteger() throws Exception {
      final int defaultValue = 777;
      final PropertyReader propertyReader = newSingletonPropertyReader("other");

      assertThat(
          propertyReader.readIntegerPropertyOrDefault(PRESENT_PROPERTY_KEY, defaultValue),
          is(defaultValue));
    }

}