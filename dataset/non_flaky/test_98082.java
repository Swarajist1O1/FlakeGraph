class DummyClass_98082 {
  @Test
  public void testSimpleAndAdvancedWriteConcern() {
    WriteConcern expected = WriteConcern.JOURNALED;
    JsonObject config = new JsonObject();
    config.put("w", "majority");
    config.put("wtimeoutMS", 1);
    config.put("j", true);
    // this overwrites the other options
    config.put("writeConcern", "journaled");

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(expected, wc);
  }

}