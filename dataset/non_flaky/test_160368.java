class DummyClass_160368 {
  @Test
  public void shouldReadMetadataFromCompleteJson() throws IOException {
    final String minimalJson =
        Resources.toString(Resources.getResource("format1_complete.json"), StandardCharsets.UTF_8);

    JsonNode jsonNode = mapper.readTree(minimalJson);
    JsonNode metadataJson = jsonNode.get("metadata");
    Metadata metadata = mapper.treeToValue(metadataJson, Metadata.class);
    assertThat(metadata).isEqualTo(new Metadata(INTERCHANGE_VERSION, GENESIS_ROOT));

    List<SigningHistory> completeSigningHistories =
        Arrays.asList(mapper.readValue(jsonNode.get("data").toString(), SigningHistory[].class));

    assertThat(completeSigningHistories)
        .containsExactly(
            new SigningHistory(
                blsPubKey,
                List.of(
                    new SignedBlock(
                        UInt64.valueOf(81952),
                        Bytes32.fromHexString(
                            "0x0000000000000000000000000000000000000000000000000000000000001234"))),
                List.of(
                    new SignedAttestation(
                        UInt64.valueOf(2290),
                        UInt64.valueOf(3007),
                        Bytes32.fromHexString(
                            "0x0000000000000000000000000000000000000000000000000000000000000123")))));
  }

}