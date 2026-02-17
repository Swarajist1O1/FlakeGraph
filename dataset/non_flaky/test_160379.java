class DummyClass_160379 {
  @Test
  public void shouldSerialize() throws JsonProcessingException {
    final SignedBlock signedBlock = new SignedBlock(slot, signingRoot);
    String str = jsonProvider.objectToPrettyJSON(signedBlock);
    assertThat(str).isEqualToNormalizingNewlines(jsonData);
  }

}