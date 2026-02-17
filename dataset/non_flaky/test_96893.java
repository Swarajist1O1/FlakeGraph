class DummyClass_96893 {
  @Test
  public void testVisit6() {
    String s6 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"ss2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"long\"}" +
        "]}";
    Assert.assertEquals("c1.ss2.!",
        Schemas.visit(new Schema.Parser().parse(s6), new TestVisitor()));

  }

}