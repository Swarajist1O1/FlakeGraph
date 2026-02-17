class DummyClass_160376 {
  @Test
  public void shouldDeserialize() throws JsonProcessingException {
    final Metadata metadata = jsonProvider.jsonToObject(jsonData, Metadata.class);
    assertThat(metadata.interchangeFormatVersion).isEqualTo(INTERCHANGE_VERSION);
    assertThat(metadata.genesisValidatorsRoot).isEqualTo(root);
  }

}