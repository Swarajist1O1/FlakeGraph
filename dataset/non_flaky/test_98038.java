class DummyClass_98038 {
  @Test
  public void testOptions() {
    AggregateOptions options = new AggregateOptions();

    long maxTime = TestUtils.randomLong();
    assertEquals(options, options.setMaxTime(maxTime));
    assertEquals(maxTime, options.getMaxTime());
  }

}