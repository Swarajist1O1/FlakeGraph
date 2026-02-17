class DummyClass_98039 {
  @Test
  public void testDefaultOptions() {
    AggregateOptions options = new AggregateOptions();
    assertEquals(AggregateOptions.DEFAULT_MAX_TIME, options.getMaxTime());
  }

}