class DummyClass_96884 {
  @Test(expected = IllegalStateException.class)
  public void testCloningError1() {
    // Visit Terminal with union
    Schema recordSchema = new Schema.Parser().parse(
        "{\"type\": \"record\", \"name\": \"R\", \"fields\":[{\"name\": \"f1\", \"type\": [\"int\", \"long\"]}]}");
    new CloningVisitor(recordSchema).visitTerminal(recordSchema.getField("f1").schema());
  }

}