class DummyClass_160375 {
  @Test
  public void shouldSerializeCompleteFormat() throws JsonProcessingException {
    final Metadata metadata = new Metadata(INTERCHANGE_VERSION, root);
    assertThat(jsonProvider.objectToPrettyJSON(metadata)).isEqualToNormalizingNewlines(jsonData);
  }

}