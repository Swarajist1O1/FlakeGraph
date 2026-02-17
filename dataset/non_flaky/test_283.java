class DummyClass_283 {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() throws IOException {
    new OffsetRange(-3, -1);
  }

}