class DummyClass_60954 {
  @Test
  public void testApplyLastTwoDayNoRows()
  {
    List<Row> expectedDay1 = Arrays.asList(JAN_1_M_10, JAN_1_F_20);
    List<Row> expectedDay2 = Collections.singletonList(JAN_2_M_10);
    List<Row> expectedDay3 = Collections.emptyList();
    List<Row> expectedDay4 = Collections.emptyList();

    rows = new ArrayList<>();
    rows.add(JAN_1_M_10);
    rows.add(JAN_1_F_20);
    rows.add(JAN_2_M_10);

    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_4);

    Sequence<Row> seq = Sequences.simple(rows);
    RowBucketIterable rbi = new RowBucketIterable(seq, intervals, ONE_DAY);
    Iterator<RowBucket> iter = rbi.iterator();

    RowBucket actual = iter.next();
    Assert.assertEquals(expectedDay1, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(expectedDay2, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(JAN_3, actual.getDateTime());
    Assert.assertEquals(expectedDay3, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(JAN_4, actual.getDateTime());
    Assert.assertEquals(expectedDay4, actual.getRows());
  }

}