class DummyClass_281 {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() throws IOException {
    new OffsetRange(0, 0);
  }

}