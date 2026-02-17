class DummyClass_98309 {
  @Test
  public void testImageWithDigest() {
    final ImageRef sut = new ImageRef("bar@sha256:12345");
    assertThat(sut.getImage(), equalTo("bar@sha256:12345"));
  }

}