class DummyClass_160370 {
  @Test
  public void shouldSerialize() throws JsonProcessingException {
    final SignedAttestation signedAttestation = new SignedAttestation(source, target, signingRoot);
    String str = jsonProvider.objectToPrettyJSON(signedAttestation);
    assertThat(str).isEqualToNormalizingNewlines(jsonData);
  }

}