class DummyClass_96907 {
  @Test(expected = IllegalArgumentException.class)
  public void testGetUnresolvedSchemaNameError() {
    Schema s = SchemaBuilder.fixed("a").size(10);
    SchemaResolver.getUnresolvedSchemaName(s);
  }

}