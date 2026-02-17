class DummyClass_60955 {
  @Test
  public void testApplyMultipleInterval()
  {
    intervals = new ArrayList<>();
    intervals.add(INTERVAL_JAN_1_4);
    intervals.add(INTERVAL_JAN_6_8);

    List<Row> expectedDay1 = Arrays.asList(JAN_1_M_10, JAN_1_F_20);
    List<Row> expectedDay2 = Collections.singletonList(JAN_2_M_10);
    List<Row> expectedDay3 = Collections.singletonList(JAN_3_F_20);
    List<Row> expectedDay4 = Arrays.asList(JAN_4_M_10, JAN_4_F_20, JAN_4_U_30);
    List<Row> expectedDay6 = Collections.singletonList(JAN_6_M_10);
    List<Row> expectedDay7 = Collections.singletonList(JAN_7_F_20);
    List<Row> expectedDay8 = Collections.singletonList(JAN_8_U_30);

    rows = new ArrayList<>();
    rows.add(JAN_1_M_10);
    rows.add(JAN_1_F_20);
    rows.add(JAN_2_M_10);
    rows.add(JAN_3_F_20);
    rows.add(JAN_4_M_10);
    rows.add(JAN_4_F_20);
    rows.add(JAN_4_U_30);
    rows.add(JAN_6_M_10);
    rows.add(JAN_7_F_20);
    rows.add(JAN_8_U_30);

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

    actual = iter.next();
    Assert.assertEquals(expectedDay6, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(expectedDay7, actual.getRows());

    actual = iter.next();
    Assert.assertEquals(expectedDay8, actual.getRows());
  }

}