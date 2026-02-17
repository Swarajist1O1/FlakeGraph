class DummyClass_60958 {
  @Test
  public void testSerde() throws Exception
  {
    TDigestSketchToQuantilePostAggregator there =
        new TDigestSketchToQuantilePostAggregator("post", new ConstantPostAggregator("", 100), 0.5);

    DefaultObjectMapper mapper = new DefaultObjectMapper();
    TDigestSketchToQuantilePostAggregator andBackAgain = mapper.readValue(
        mapper.writeValueAsString(there),
        TDigestSketchToQuantilePostAggregator.class
    );

    Assert.assertEquals(there, andBackAgain);
    Assert.assertArrayEquals(there.getCacheKey(), andBackAgain.getCacheKey());
    Assert.assertEquals(there.getDependentFields(), andBackAgain.getDependentFields());
  }

}