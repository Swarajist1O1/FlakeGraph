class DummyClass_160328 {
    @Test
    public void shouldReturnValueWhenKeyIsPresent() throws Exception {
      final boolean value = true;
      final PropertyReader propertyReader = newSingletonPropertyReader(String.valueOf(value));

      assertThat(
          propertyReader.readBooleanPropertyOrDefault(PRESENT_PROPERTY_KEY, false), is(value));
    }

}