class DummyClass_160322 {
    @Test
    public void shouldThrowExceptionWhenKeyIsEmptyOrOnlyWhitespace() {
      assertThrows(IllegalArgumentException.class, () -> propertyReader.readProperty(""));
      assertThrows(IllegalArgumentException.class, () -> propertyReader.readProperty("    "));
    }

}