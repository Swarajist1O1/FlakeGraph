class DummyClass_160373 {
  @Test
  public void shouldSerializeMinimalFormat() throws JsonProcessingException {
    final Metadata metadata = new Metadata(INTERCHANGE_VERSION, root);
    assertThat(jsonProvider.objectToPrettyJSON(metadata)).isEqualToNormalizingNewlines(jsonData);
  }

}