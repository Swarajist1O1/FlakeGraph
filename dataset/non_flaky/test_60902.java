class DummyClass_60902 {
  @Test
  public void testCompleteData()
  {

    Map<String, Object> event1 = new HashMap<>();
    Map<String, Object> event2 = new HashMap<>();
    Map<String, Object> event3 = new HashMap<>();

    event1.put("gender", "m");
    event1.put("pageViews", 10L);
    event2.put("gender", "f");
    event2.put("pageViews", 20L);
    event3.put("gender", "u");
    event3.put("pageViews", 30L);

    List<DimensionSpec> ds = new ArrayList<>();
    ds.add(new DefaultDimensionSpec("gender", "gender"));

    Row jan1Row1 = new MapBasedRow(JAN_1, event1);
    Row jan1Row2 = new MapBasedRow(JAN_1, event2);
    Row jan1Row3 = new MapBasedRow(JAN_1, event3);

    Row jan2Row1 = new MapBasedRow(JAN_2, event1);
    Row jan2Row2 = new MapBasedRow(JAN_2, event2);
    Row jan2Row3 = new MapBasedRow(JAN_2, event3);

    Sequence<RowBucket> seq = Sequences.simple(Arrays.asList(
        new RowBucket(JAN_1, Arrays.asList(jan1Row1, jan1Row2, jan1Row3)),
        new RowBucket(JAN_2, Arrays.asList(jan2Row1, jan2Row2, jan2Row3))
    ));

    Iterator<Row> iter = new MovingAverageIterable(
        seq,
        ds,
        Collections.singletonList(
            new LongMeanAveragerFactory("movingAvgPageViews", 2, 1, "pageViews")
        ),
        Collections.emptyList(),
        Collections.singletonList(new LongSumAggregatorFactory("pageViews", "pageViews"))
    ).iterator();

    Assert.assertTrue(iter.hasNext());
    Row result = iter.next();
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(JAN_1, (result.getTimestamp()));

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals("f", (result.getDimension("gender")).get(0));
    Assert.assertEquals(JAN_1, (result.getTimestamp()));

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals("u", (result.getDimension("gender")).get(0));
    Assert.assertEquals(JAN_1, (result.getTimestamp()));

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals("m", (result.getDimension("gender")).get(0));
    Assert.assertEquals(JAN_2, (result.getTimestamp()));

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals("f", (result.getDimension("gender")).get(0));
    Assert.assertEquals(JAN_2, (result.getTimestamp()));

    Assert.assertTrue(iter.hasNext());
    result = iter.next();
    Assert.assertEquals("u", (result.getDimension("gender")).get(0));
    Assert.assertEquals(JAN_2, (result.getTimestamp()));

    Assert.assertFalse(iter.hasNext());

  }

}