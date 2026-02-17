class DummyClass_160326 {
    @Test
    public void shouldReturnValueWhenKeyIsPresent() throws Exception {
      final String value = "value";
      final PropertyReader propertyReader = newSingletonPropertyReader(value);

      assertThat(
          propertyReader.readPropertyOrDefault(PRESENT_PROPERTY_KEY, "defaultValue"), is(value));
    }

}