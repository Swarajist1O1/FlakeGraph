class DummyClass_98269 {
  @Test
  public void testLoadInvalidJson() throws Exception {
    expectedException.expect(JsonParseException.class);
    objectMapper.readValue(fixture("fixtures/invalid.json"), ContainerState.class);

  }

}