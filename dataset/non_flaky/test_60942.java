class DummyClass_60942 {
  @Test
  public void testApplyLastDaySingleRow()
  {
    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_4);

    List<Row> expectedDay1 = Arrays.asList(JAN_1_M_10, JAN_1_F_20);
    List<Row> expectedDay2 = Collections.singletonList(JAN_2_M_10);
    List<Row> expectedDay3 = Collections.singletonList(JAN_3_F_20);
    List<Row> expectedDay4 = Collections.singletonList(JAN_4_M_10);

    rows = new ArrayList<>();
    rows.add(JAN_1_M_10);
    rows.add(JAN_1_F_20);
    rows.add(JAN_2_M_10);
    rows.add(JAN_3_F_20);
    rows.add(JAN_4_M_10);

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    RowBucket actual = iter.next();
    Assert.assertEquals(expectedDay1, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(expectedDay2, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(expectedDay3, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(expectedDay4, actual.getRows());
  }

}