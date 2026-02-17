class DummyClass_160380 {
  @Test
  public void shouldDeserialize() throws JsonProcessingException {
    final SignedBlock signedBlock = jsonProvider.jsonToObject(jsonData, SignedBlock.class);
    assertThat(signedBlock.slot).isEqualTo(slot);
    assertThat(signedBlock.signingRoot).isEqualTo(signingRoot);
  }

}