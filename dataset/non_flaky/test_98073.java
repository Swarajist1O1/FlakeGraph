class DummyClass_98073 {
  @Test
  public void testNoWriteConcern() {
    WriteConcern wc = new WriteConcernParser(null, new JsonObject()).writeConcern();
    assertNull(wc);
  }

}