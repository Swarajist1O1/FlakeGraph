class DummyClass_160369 {
  @Test
  public void shouldCreate() {
    final SignedAttestation signedAttestation = new SignedAttestation(source, target, signingRoot);
    assertThat(signedAttestation.sourceEpoch).isEqualTo(source);
    assertThat(signedAttestation.targetEpoch).isEqualTo(target);
    assertThat(signedAttestation.signingRoot).isEqualTo(signingRoot);
  }

}