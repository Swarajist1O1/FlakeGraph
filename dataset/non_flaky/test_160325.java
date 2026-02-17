class DummyClass_160325 {
    @Test
    public void shouldReturnEmptyWhenKeyIsAbsent() {
      assertThat(propertyReader.readProperty(ABSENT_PROPERTY_KEY), is(emptyString()));
    }

}