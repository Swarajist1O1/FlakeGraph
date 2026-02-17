class DummyClass_60961 {
  @Test
  public void testSerde() throws Exception
  {
    TDigestSketchToQuantilesPostAggregator there =
        new TDigestSketchToQuantilesPostAggregator("post", new ConstantPostAggregator("", 100), new double[]{0.25, 0.75});

    DefaultObjectMapper mapper = new DefaultObjectMapper();
    TDigestSketchToQuantilesPostAggregator andBackAgain = mapper.readValue(
        mapper.writeValueAsString(there),
        TDigestSketchToQuantilesPostAggregator.class
    );

    Assert.assertEquals(there, andBackAgain);
    Assert.assertArrayEquals(there.getCacheKey(), andBackAgain.getCacheKey());
    Assert.assertEquals(there.getDependentFields(), andBackAgain.getDependentFields());
  }

}