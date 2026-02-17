class DummyClass_162649 {
  @Test
  public void doesNotSetDuplicateAttributes() {
    // given
    Map<String, String> request = new HashMap<>();
    request.put("transport", "TCP");
    request.put("peerName", "1.2.3.4");
    request.put("peerIp", "1.2.3.4");
    request.put("peerPort", "123");

    Map<String, String> response = new HashMap<>();
    response.put("peerName", "4.3.2.1");
    response.put("peerPort", "42");
    response.put("peerIp", "4.3.2.1");

    TestNetClientAttributesExtractor extractor = new TestNetClientAttributesExtractor();

    // when
    AttributesBuilder startAttributes = Attributes.builder();
    extractor.onStart(startAttributes, request);

    AttributesBuilder endAttributes = Attributes.builder();
    extractor.onEnd(endAttributes, request, response, null);

    // then
    assertThat(startAttributes.build()).isEmpty();

    assertThat(endAttributes.build())
        .containsOnly(
            entry(SemanticAttributes.NET_PEER_PORT, 42L),
            entry(SemanticAttributes.NET_PEER_IP, "4.3.2.1"));
  }

}