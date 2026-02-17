class DummyClass_285 {
  @Test
  public void testCompare() throws IOException {
    OffsetRange r1 = new OffsetRange(0, 1);
    OffsetRange r2 = new OffsetRange(1, 3);
    OffsetRange r3 = new OffsetRange(1, 3);
    OffsetRange r4 = new OffsetRange(3, 4);

    assertEquals(0, OffsetRange.ReverseComparatorOnMin.compare(r2, r3));
    assertEquals(0, OffsetRange.ReverseComparatorOnMin.compare(r2, r2));
    assertTrue(OffsetRange.ReverseComparatorOnMin.compare(r2, r1) < 0);
    assertTrue(OffsetRange.ReverseComparatorOnMin.compare(r2, r4) > 0);
  }

}