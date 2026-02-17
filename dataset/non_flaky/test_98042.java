class DummyClass_98042 {
  @Test
  public void testCopyOptions() {
    AggregateOptions options = new AggregateOptions();
    options.setMaxTime(TestUtils.randomLong());

    AggregateOptions copy = new AggregateOptions(options);
    assertEquals(options.getMaxTime(), copy.getMaxTime());
  }

}