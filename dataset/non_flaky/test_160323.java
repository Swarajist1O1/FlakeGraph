class DummyClass_160323 {
    @Test
    public void shouldReturnValueWhenKeyIsPresent() {
      assertThat(propertyReader.readProperty(PRESENT_PROPERTY_KEY), is(PRESENT_PROPERTY_VALUE));
    }

}