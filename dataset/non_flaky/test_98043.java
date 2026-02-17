class DummyClass_98043 {
  @Test
  public void testToJson() {
    AggregateOptions options = new AggregateOptions();
    long maxTime = TestUtils.randomPositiveLong();
    options.setMaxTime(maxTime);

    assertEquals(options, new AggregateOptions(options.toJson()));
  }

}