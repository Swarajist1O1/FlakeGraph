class DummyClass_98040 {
  @Test
  public void testOptionsJson() {
    JsonObject json = new JsonObject();

    long maxAwaitTime = TestUtils.randomLong();
    json.put("maxAwaitTime", maxAwaitTime);

    long maxTime = TestUtils.randomLong();
    json.put("maxTime", maxTime);

    AggregateOptions options = new AggregateOptions(json);
    assertEquals(maxTime, options.getMaxTime());
  }

}