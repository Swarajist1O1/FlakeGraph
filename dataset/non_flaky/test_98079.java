class DummyClass_98079 {
  @Test
  public void testAdvancedWriteConcern_w_string() {
    WriteConcern expected = WriteConcern.MAJORITY.withWTimeout(1, TimeUnit.MILLISECONDS).withJournal(true);
    JsonObject config = new JsonObject();
    config.put("w", "majority");
    config.put("wtimeoutMS", 1);
    config.put("j", true);

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(expected, wc);
  }

}