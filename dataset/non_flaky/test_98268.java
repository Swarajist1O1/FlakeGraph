class DummyClass_98268 {
  @Test
  public void testLoadInvalidConatainerStateJson() throws Exception {
    expectedException.expect(JsonMappingException.class);
    objectMapper.readValue(fixture("fixtures/container-state-invalid.json"), ContainerState.class);

  }

}