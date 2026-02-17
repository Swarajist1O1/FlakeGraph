class DummyClass_60911 {
  @Test
  public void testApplyMissingColumn()
  {
    event.put("count", 10.0);

    Row result = pac.apply(row);

    Assert.assertEquals(0.0, result.getMetric("avgCountRatio").floatValue(), 0.0);
    Assert.assertNull(result.getRaw("avgCountRatio"));
  }

}