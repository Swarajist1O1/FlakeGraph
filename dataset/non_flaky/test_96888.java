class DummyClass_96888 {
  @Test
  public void testVisit1() {
    String s1 = "{\"type\": \"record\", \"name\": \"t1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": \"int\"}" +
        "]}";
    Assert.assertEquals("t1.", Schemas.visit(new Schema.Parser().parse(s1), new TestVisitor()));
  }

}