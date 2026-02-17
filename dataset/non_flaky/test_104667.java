class DummyClass_104667 {
  @Test
  public void testCountRecords() {
    long count =
        getPinotConnection().execute(new Request("sql", "SELECT COUNT(*) FROM " + getTableName())).getResultSet(0)
            .getLong(0);

    Assert.assertEquals(count, _totalRecordsPushedInStream);
  }

}