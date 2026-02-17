class DummyClass_104615 {
  @Test
  public void testCountWithNullDescription()
      throws Exception {
    String query = "SELECT count(*) FROM " + getTableName() + " where description IS NOT NULL";
    testQuery(query, Collections.singletonList(query));
  }

}