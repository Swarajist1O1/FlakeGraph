class DummyClass_160377 {
  @Test
  public void shouldReadMetadataFromCompleteJson() throws IOException {
    final String completeJson =
        Resources.toString(Resources.getResource("format1_complete.json"), StandardCharsets.UTF_8);

    JsonNode metadataJson = mapper.readTree(completeJson).get("metadata");
    Metadata metadata = mapper.treeToValue(metadataJson, Metadata.class);

    assertThat(metadata).isEqualTo(expectedMetadata);
  }

}