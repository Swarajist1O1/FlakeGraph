class DummyClass_60960 {
  @Test
  public void testEquals()
  {
    EqualsVerifier.forClass(TDigestSketchToQuantilePostAggregator.class)
                  .withNonnullFields("name", "field", "fraction")
                  .usingGetClass()
                  .verify();
  }

}