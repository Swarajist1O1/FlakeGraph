class DummyClass_98041 {
  @Test
  public void testDefaultOptionsJson() {
    AggregateOptions options = new AggregateOptions(new JsonObject());
    AggregateOptions def = new AggregateOptions();
    assertEquals(def.getMaxTime(), options.getMaxTime());
  }

}