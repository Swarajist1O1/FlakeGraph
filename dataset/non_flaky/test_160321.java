class DummyClass_160321 {
    @Test
    public void shouldThrowExceptionWhenKeyIsNull() {
      assertThrows(NullPointerException.class, () -> propertyReader.readProperty(null));
    }

}