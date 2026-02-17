class DummyClass_60946 {
  @Test
  public void testMissingDaysAtBegining()
  {
    List<Row> expectedDay1 = Collections.emptyList();
    List<Row> expectedDay2 = Collections.singletonList(JAN_2_M_10);

    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_2);

    rows = new ArrayList<>();
    rows.add(JAN_2_M_10);

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    RowBucket actual = iter.next();
    Assert.assertEquals(JAN_1, actual.getDateTime());
    Assert.assertEquals(expectedDay1, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(JAN_2, actual.getDateTime());
    Assert.assertEquals(expectedDay2, actual.getRows());
  }

}