class DummyClass_104614 {
  @Test
  public void testTotalCount()
      throws Exception {
    String query = "SELECT count(*) FROM " + getTableName();
    testQuery(query, Collections.singletonList(query));
  }

}