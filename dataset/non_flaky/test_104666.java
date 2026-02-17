class DummyClass_104666 {
  @Test
  public void testRecords()
      throws Exception {
    Assert.assertNotEquals(_totalRecordsPushedInStream, 0);

    ResultSet pinotResultSet = getPinotConnection()
        .execute(new Request("sql", "SELECT * FROM " + getTableName() + " ORDER BY Origin LIMIT 10000"))
        .getResultSet(0);

    Assert.assertNotEquals(pinotResultSet.getRowCount(), 0);

    Statement h2statement =
        _h2Connection.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
    h2statement.execute("SELECT * FROM " + getTableName() + " ORDER BY Origin");
    java.sql.ResultSet h2ResultSet = h2statement.getResultSet();

    Assert.assertFalse(h2ResultSet.isLast());

    h2ResultSet.beforeFirst();
    int row = 0;
    Map<String, Integer> columnToIndex = new HashMap<>();
    for (int i = 0; i < _h2FieldNameAndTypes.size(); i++) {
      columnToIndex.put(pinotResultSet.getColumnName(i), i);
    }

    while (h2ResultSet.next()) {

      for (String fieldNameAndDatatype : _h2FieldNameAndTypes) {
        String[] fieldNameAndDatatypeList = fieldNameAndDatatype.split(" ");
        String fieldName = fieldNameAndDatatypeList[0];
        String h2DataType = fieldNameAndDatatypeList[1];
        switch (h2DataType) {
          case "int": {
            int expectedValue = h2ResultSet.getInt(fieldName);
            int actualValue = pinotResultSet.getInt(row, columnToIndex.get(fieldName));
            Assert.assertEquals(expectedValue, actualValue);
            break;
          }
          case "varchar(128)": {
            String expectedValue = h2ResultSet.getString(fieldName);
            String actualValue = pinotResultSet.getString(row, columnToIndex.get(fieldName));
            Assert.assertEquals(expectedValue, actualValue);
            break;
          }
          default:
            break;
        }
      }

      row++;

      if (row >= pinotResultSet.getRowCount()) {
        int cnt = 0;
        while (h2ResultSet.next()) {
          cnt++;
        }
        Assert.assertEquals(cnt, 0);
        break;
      }
    }
  }

}