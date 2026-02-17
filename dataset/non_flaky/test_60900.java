class DummyClass_60900 {
  @Test
  public void testNext()
  {

    List<DimensionSpec> dims = Arrays.asList(
        new DefaultDimensionSpec(GENDER, GENDER),
        new DefaultDimensionSpec(AGE, AGE),
        new DefaultDimensionSpec(COUNTRY, COUNTRY)
    );

    Sequence<RowBucket> dayBuckets = Sequences.simple(Arrays.asList(
        new RowBucket(JAN_1, Arrays.asList(
            new MapBasedRow(JAN_1, DIMS1),
            new MapBasedRow(JAN_1, DIMS2)
        )),
        new RowBucket(JAN_2, Collections.singletonList(
            new MapBasedRow(JAN_2, DIMS1)
        )),
        new RowBucket(JAN_3, Collections.emptyList()),
        new RowBucket(JAN_4, Arrays.asList(
            new MapBasedRow(JAN_4, DIMS2),
            new MapBasedRow(JAN_4, DIMS3)
        ))
    ));

    Iterable<Row> iterable = new MovingAverageIterable(
        dayBuckets,
        dims,
        Collections.singletonList(new ConstantAveragerFactory("noop", 1, 1.1f)),
        Collections.emptyList(),
        Collections.emptyList()
    );

    Iterator<Row> iter = iterable.iterator();

    Assert.assertTrue(iter.hasNext());
    Row r = iter.next();
    Assert.assertEquals(JAN_1, r.getTimestamp());
    Assert.assertEquals("m", r.getRaw(GENDER));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_1, r.getTimestamp());
    Assert.assertEquals("f", r.getRaw(GENDER));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_2, r.getTimestamp());
    Assert.assertEquals("m", r.getRaw(GENDER));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_2, r.getTimestamp());
    Assert.assertEquals("f", r.getRaw(GENDER));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Row r2 = r;
    Assert.assertEquals(JAN_3, r.getTimestamp());
    Assert.assertEquals("US", r.getRaw(COUNTRY));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_3, r.getTimestamp());
    Assert.assertEquals("US", r.getRaw(COUNTRY));
    Assert.assertThat(r.getRaw(AGE), CoreMatchers.not(CoreMatchers.equalTo(r2.getRaw(AGE))));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_4, r.getTimestamp());
    Assert.assertEquals("f", r.getRaw(GENDER));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_4, r.getTimestamp());
    Assert.assertEquals("u", r.getRaw(GENDER));

    Assert.assertTrue(iter.hasNext());
    r = iter.next();
    Assert.assertEquals(JAN_4, r.getTimestamp());
    Assert.assertEquals("m", r.getRaw(GENDER));

    Assert.assertFalse(iter.hasNext());
  }

}