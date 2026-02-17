class DummyClass_60910 {
  @Test
  public void testApply()
  {
    event.put("count", 10.0);
    event.put("avgCount", 12.0);

    Row result = pac.apply(row);

    Assert.assertEquals(10.0f / 12.0f, result.getMetric("avgCountRatio").floatValue(), 0.0);
  }

}