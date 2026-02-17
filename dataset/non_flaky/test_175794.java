class DummyClass_175794 {
  @Test
  public void testConstructor_nonAbsoluteBasePath() {
    try {
      new RelativeFileFieldSetter(field, new Path("non/absolute/base/path"), dialog);
      fail();
    } catch (IllegalArgumentException ex) {}
  }

}