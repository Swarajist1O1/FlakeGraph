class DummyClass_176867 {
  @Test(expected = IllegalArgumentException.class)
  public void testNoSuchMethod() {
    ClassUtils.loadInstanceOf(Long.class.getName(), Long.class);
  }

}