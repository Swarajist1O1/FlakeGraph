class DummyClass_162650 {
  @Test
  public void doesNotSetNegativePort() {
    // given
    Map<String, String> request = new HashMap<>();
    request.put("peerPort", "-42");

    Map<String, String> response = new HashMap<>();
    response.put("peerPort", "-1");

    TestNetClientAttributesExtractor extractor = new TestNetClientAttributesExtractor();

    // when
    AttributesBuilder startAttributes = Attributes.builder();
    extractor.onStart(startAttributes, request);

    AttributesBuilder endAttributes = Attributes.builder();
    extractor.onEnd(endAttributes, request, response, null);

    // then
    assertThat(startAttributes.build()).isEmpty();
    assertThat(endAttributes.build()).isEmpty();
  }

}