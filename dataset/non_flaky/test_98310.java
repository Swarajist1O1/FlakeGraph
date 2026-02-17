class DummyClass_98310 {
  @Test
  public void testImageWithDigestAndRegistry() {
    final ImageRef sut = new ImageRef("registry:4711/foo/bar@sha256:12345");
    assertThat(sut.getImage(), equalTo("registry:4711/foo/bar@sha256:12345"));
  }

}