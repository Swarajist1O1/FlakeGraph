class DummyClass_60945 {
  @Test
  public void testSingleDayMultipleRow()
  {
    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_1);

    rows = new ArrayList<>();
    rows.add(JAN_1_M_10);
    rows.add(JAN_1_F_20);
    rows.add(JAN_1_U_30);

    List<Row> expectedDay1 = Arrays.asList(JAN_1_M_10, JAN_1_F_20, JAN_1_U_30);

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    RowBucket actual = iter.next();
    Assert.assertEquals(JAN_1, actual.getDateTime());
    Assert.assertEquals(expectedDay1, actual.getRows());
  }

}