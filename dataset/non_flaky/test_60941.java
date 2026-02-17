class DummyClass_60941 {
  @Test
  public void testCompleteData()
  {
    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_4);

    rows = new ArrayList<>();
    rows.add(JAN_1_M_10);
    rows.add(JAN_2_M_10);
    rows.add(JAN_3_M_10);
    rows.add(JAN_4_M_10);

    List<Row> expectedDay1 = Collections.singletonList(JAN_1_M_10);
    List<Row> expectedDay2 = Collections.singletonList(JAN_2_M_10);
    List<Row> expectedDay3 = Collections.singletonList(JAN_3_M_10);
    List<Row> expectedDay4 = Collections.singletonList(JAN_4_M_10);

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    RowBucket actual = iter.next();
    Assert.assertEquals(JAN_1, actual.getDateTime());
    Assert.assertEquals(expectedDay1, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(JAN_2, actual.getDateTime());
    Assert.assertEquals(expectedDay2, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(JAN_3, actual.getDateTime());
    Assert.assertEquals(expectedDay3, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(JAN_4, actual.getDateTime());
    Assert.assertEquals(expectedDay4, actual.getRows());
  }

}