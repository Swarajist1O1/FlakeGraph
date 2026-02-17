class DummyClass_60909 {
  @Test
  public void testMissingDaysAtEnd()
  {
    System.setProperty("druid.generic.useDefaultValueForNull", "true");
    NullHandling.initializeForTests();
    Map<String, Object> event1 = new HashMap<>();
    Map<String, Object> event2 = new HashMap<>();

    List<DimensionSpec> ds = new ArrayList<>();
    ds.add(new DefaultDimensionSpec("gender", "gender"));

    event1.put("gender", "m");
    event1.put("pageViews", 10L);
    Row row1 = new MapBasedRow(JAN_1, event1);

    event2.put("gender", "m");
    event2.put("pageViews", 20L);
    Row row2 = new MapBasedRow(JAN_2, event2);

    Sequence<RowBucket> seq = Sequences.simple(Arrays.asList(
        new RowBucket(JAN_1, Collections.singletonList(row1)),
        new RowBucket(JAN_2, Collections.singletonList(row2)),
        new RowBucket(JAN_3, Collections.emptyList()),
        new RowBucket(JAN_4, Collections.emptyList()),
        new RowBucket(JAN_5, Collections.emptyList()),
        new RowBucket(JAN_6, Collections.emptyList())
    ));

    Iterator<Row> iter = new MovingAverageIterable(
        seq,
        ds,
        Collections.singletonList(
            new LongMeanAveragerFactory("movingAvgPageViews", 4, 1, "pageViews")
        ),
        Collections.emptyList(),
        Collections.singletonList(new LongSumAggregatorFactory("pageViews", "pageViews"))
    ).iterator();

    Assert.assertTrue(iter.hasNext());
    Row result = iter.next();

    Assert.assertEquals(JAN_1, result.getTimestamp());
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(2.5f, result.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals(JAN_2, result.getTimestamp());
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(7.5f, result.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals(JAN_3, result.getTimestamp());
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(7.5f, result.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals(JAN_4, result.getTimestamp());
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(7.5f, result.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals(JAN_5, result.getTimestamp());
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(5.0f, result.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals(JAN_6, result.getTimestamp());
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(0.0f, result.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertFalse(iter.hasNext());
  }

}