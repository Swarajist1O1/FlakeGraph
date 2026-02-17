class DummyClass_160330 {
    @Test
    public void shouldReturnValueWhenKeyIsPresent() throws Exception {
      final int value = 42;
      final PropertyReader propertyReader = newSingletonPropertyReader(String.valueOf(value));

      assertThat(propertyReader.readIntegerPropertyOrDefault(PRESENT_PROPERTY_KEY, -1), is(value));
    }

}