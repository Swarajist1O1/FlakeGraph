class DummyClass_98306 {
  @Test
  public void testImageWithoutTag() {
    final ImageRef sut = new ImageRef("foobar");
    assertThat(sut.getImage(), equalTo("foobar"));
    assertThat(sut.getTag(), is(nullValue()));
  }

}