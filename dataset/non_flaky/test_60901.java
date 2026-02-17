class DummyClass_60901 {
  @Test
  public void testAveraging()
  {

    Map<String, Object> event1 = new HashMap<>();
    Map<String, Object> event2 = new HashMap<>();
    Map<String, Object> event3 = new HashMap<>();
    Map<String, Object> event4 = new HashMap<>();

    List<DimensionSpec> ds = new ArrayList<>();
    ds.add(new DefaultDimensionSpec("gender", "gender"));

    event1.put("gender", "m");
    event1.put("pageViews", 10L);
    Row row1 = new MapBasedRow(JAN_1, event1);

    event2.put("gender", "m");
    event2.put("pageViews", 20L);
    Row row2 = new MapBasedRow(JAN_2, event2);

    event3.put("gender", "m");
    event3.put("pageViews", 30L);
    Row row3 = new MapBasedRow(JAN_3, event3);

    event4.put("gender", "f");
    event4.put("pageViews", 40L);
    Row row4 = new MapBasedRow(JAN_3, event4);

    float retval = 14.5f;

    Sequence<RowBucket> seq = Sequences.simple(Arrays.asList(
        new RowBucket(JAN_1, Collections.singletonList(row1)),
        new RowBucket(JAN_2, Collections.singletonList(row2)),
        new RowBucket(JAN_3, Arrays.asList(row3, row4))
    ));

    Iterator<Row> iter = new MovingAverageIterable(
        seq,
        ds,
        Arrays.asList(
            new ConstantAveragerFactory("costPageViews", 7, retval),
            new LongMeanAveragerFactory("movingAvgPageViews", 7, 1, "pageViews")
        ),
        Collections.emptyList(),
        Collections.singletonList(new LongSumAggregatorFactory("pageViews", "pageViews"))
    ).iterator();

    Assert.assertTrue(iter.hasNext());
    Row caResult = iter.next();

    Assert.assertEquals(JAN_1, caResult.getTimestamp());
    Assert.assertEquals("m", (caResult.getDimension("gender")).get(0));
    Assert.assertEquals(retval, caResult.getMetric("costPageViews").floatValue(), 0.0f);
    Assert.assertEquals(1.4285715f, caResult.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    caResult = iter.next();
    Assert.assertEquals("m", (caResult.getDimension("gender")).get(0));
    Assert.assertEquals(4.285714f, caResult.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    caResult = iter.next();
    Assert.assertEquals("m", (caResult.getDimension("gender")).get(0));
    Assert.assertEquals(8.571428f, caResult.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertTrue(iter.hasNext());
    caResult = iter.next();
    Assert.assertEquals("f", (caResult.getDimension("gender")).get(0));
    Assert.assertEquals(5.714285850f, caResult.getMetric("movingAvgPageViews").floatValue(), 0.0f);

    Assert.assertFalse(iter.hasNext());

  }

}