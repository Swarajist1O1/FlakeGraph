class DummyClass_282 {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() throws IOException {
    new OffsetRange(-1, 0);
  }

}