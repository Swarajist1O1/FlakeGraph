class DummyClass_98078 {
  @Test
  public void testAdvancedWriteConcern_w_int() {
    WriteConcern expected = new WriteConcern(3).withWTimeout(25, TimeUnit.MILLISECONDS).withJournal(true);
    JsonObject config = new JsonObject();
    config.put("w", 3);
    config.put("wtimeoutMS", 25);
    config.put("j", true);

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(expected, wc);
  }

}