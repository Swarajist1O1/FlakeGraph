class DummyClass_96885 {
  @Test(expected = IllegalStateException.class)
  public void testCloningError2() {
    // After visit Non-terminal with int
    Schema recordSchema = new Schema.Parser().parse(
        "{\"type\": \"record\", \"name\": \"R\", \"fields\":[{\"name\": \"f1\", \"type\": \"int\"}]}");
    new CloningVisitor(recordSchema).afterVisitNonTerminal(recordSchema.getField("f1").schema());
  }

}