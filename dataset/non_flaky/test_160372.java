class DummyClass_160372 {
  @Test
  public void shouldReadMetadataFromMinimalJson() throws IOException {
    final String minimalJson =
        Resources.toString(Resources.getResource("format2_minimal.json"), StandardCharsets.UTF_8);

    JsonNode jsonNode = mapper.readTree(minimalJson);
    JsonNode metadataJson = jsonNode.get("metadata");
    Metadata metadata = mapper.treeToValue(metadataJson, Metadata.class);
    assertThat(metadata).isEqualTo(new Metadata(INTERCHANGE_VERSION, GENESIS_ROOT));

    List<SigningHistory> minimalSigningHistoryList =
        Arrays.asList(mapper.readValue(jsonNode.get("data").toString(), SigningHistory[].class));

    SigningHistory element =
        new SigningHistory(
            blsPubKey,
            new ValidatorSigningRecord(
                GENESIS_ROOT, UInt64.valueOf(81952), UInt64.valueOf(2290), UInt64.valueOf(3007)));
    assertThat(minimalSigningHistoryList).containsExactly(element);
  }

}