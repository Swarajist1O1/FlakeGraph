class DummyClass_98267 {
  @Test
  public void testLoadFromRandomFixtureMissingProperty() throws Exception {
    objectMapper.readValue(fixture("fixtures/container-state-missing-property.json"),
                           ContainerState.class);
  }

}