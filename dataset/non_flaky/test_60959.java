class DummyClass_60959 {
  @Test
  public void testToString()
  {
    PostAggregator postAgg =
        new TDigestSketchToQuantilePostAggregator("post", new ConstantPostAggregator("", 100), 0.5);

    Assert.assertEquals(
        "TDigestSketchToQuantilePostAggregator{name='post', field=ConstantPostAggregator{name='', constantValue=100}, fraction=0.5}",
        postAgg.toString()
    );
  }

}