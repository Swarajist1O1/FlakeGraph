class DummyClass_160378 {
  @Test
  public void shouldReadMetadataFromJson() throws IOException {
    final String minimalJson =
        Resources.toString(Resources.getResource("format2_minimal.json"), StandardCharsets.UTF_8);

    JsonNode metadataJson = mapper.readTree(minimalJson).get("metadata");
    Metadata metadata = mapper.treeToValue(metadataJson, Metadata.class);
    assertThat(metadata).isEqualTo(expectedMetadata);
  }

}