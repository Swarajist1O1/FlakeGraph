class DummyClass_160324 {
    @Test
    public void shouldReturnTrimmedValueWhenKeyIsPresentAndValueHasLeadingAndTrailingWhitespace()
        throws Exception {
      final PropertyReader propertyReader =
          newSingletonPropertyReader("  " + PRESENT_PROPERTY_VALUE + "  ");

      assertThat(propertyReader.readProperty(PRESENT_PROPERTY_KEY), is(PRESENT_PROPERTY_VALUE));
    }

}