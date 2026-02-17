class DummyClass_96892 {
  @Test
  public void testVisit5() {
    String s5 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"c2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"long\"}" +
        "]}";
    Assert.assertEquals("c1.c2.\"int\"!\"long\"!",
        Schemas.visit(new Schema.Parser().parse(s5), new TestVisitor()));

  }

}