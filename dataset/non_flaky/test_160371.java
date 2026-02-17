class DummyClass_160371 {
  @Test
  public void shouldDeserialize() throws JsonProcessingException {
    final SignedAttestation signedAttestation =
        jsonProvider.jsonToObject(jsonData, SignedAttestation.class);
    assertThat(signedAttestation.sourceEpoch).isEqualTo(source);
    assertThat(signedAttestation.targetEpoch).isEqualTo(target);
    assertThat(signedAttestation.signingRoot).isEqualTo(signingRoot);
  }

}