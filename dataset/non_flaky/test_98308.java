class DummyClass_98308 {
  @Test
  public void testImageWithTagAndRegistry() {
    final ImageRef sut = new ImageRef("registry:4711/foo/bar:12345");
    assertThat(sut.getImage(), equalTo("registry:4711/foo/bar"));
    assertThat(sut.getTag(), is("12345"));
  }

}