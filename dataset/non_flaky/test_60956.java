class DummyClass_60956 {
  @Test
  public void testNodata()
  {
    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_4);
    intervals.add(INTERVAL_JAN_6_8);

    rows = new ArrayList<>();

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    Assert.assertTrue(iter.hasNext());
    RowBucket actual = iter.next();
    Assert.assertEquals(Collections.emptyList(), actual.getRows());
  }

}