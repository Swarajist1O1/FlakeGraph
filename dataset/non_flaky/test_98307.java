class DummyClass_98307 {
  @Test
  public void testImageWithTag() {
    final ImageRef sut = new ImageRef("foobar:12345");
    assertThat(sut.getImage(), equalTo("foobar"));
    assertThat(sut.getTag(), is("12345"));
  }

}