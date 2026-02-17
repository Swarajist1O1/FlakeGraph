class DummyClass_60944 {
  @Test
  public void testSingleDaySingleRow()
  {
    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_1);

    rows = new ArrayList<>();
    rows.add(JAN_1_M_10);

    List<Row> expectedDay1 = Collections.singletonList(JAN_1_M_10);

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    RowBucket actual = iter.next();
    Assert.assertEquals(expectedDay1, actual.getRows());
    Assert.assertEquals(JAN_1, actual.getDateTime());
  }

}