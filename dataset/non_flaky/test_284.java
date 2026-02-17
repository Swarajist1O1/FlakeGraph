class DummyClass_284 {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() throws IOException {
    new OffsetRange(-3, 100);
  }

}