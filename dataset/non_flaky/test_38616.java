class DummyClass_38616 {
    @Test
    public void TestGetTableId() throws Exception {
        String tableName = "TestGetTableId";

        sqliteUtils.createTable(
            "CREATE TABLE " + tableName + "(" +
                "    firstName  TEXT," +
                "    lastName  TEXT," +
                "    age INTEGER," +
                "    bool  NUMERIC," +
                "    byte  INTEGER," +
                "    short INTEGER NULL," +
                "    long INTEGER," +
                "    float NUMERIC," +
                "    double NUMERIC," +
                "    bytes BLOB, " +
                "PRIMARY KEY (firstName, lastName));"
        );

        Connection connection = sqliteUtils.getConnection();

        // Test getTableId
        log.info("verify getTableId");
        TableId id = JdbcUtils.getTableId(connection, tableName);
        Assert.assertEquals(id.getTableName(), tableName);

        // Test get getTableDefinition
        log.info("verify getTableDefinition");
        List<String> keyList = Lists.newArrayList();
        keyList.add("firstName");
        keyList.add("lastName");
        List<String> nonKeyList = Lists.newArrayList();
        nonKeyList.add("age");
        nonKeyList.add("long");
        TableDefinition table = JdbcUtils.getTableDefinition(connection, id, keyList, nonKeyList);
        Assert.assertEquals(table.getColumns().get(0).getName(), "firstName");
        Assert.assertEquals(table.getColumns().get(0).getTypeName(), "TEXT");
        Assert.assertEquals(table.getColumns().get(2).getName(), "age");
        Assert.assertEquals(table.getColumns().get(2).getTypeName(), "INTEGER");
        Assert.assertEquals(table.getColumns().get(7).getName(), "float");
        Assert.assertEquals(table.getColumns().get(7).getTypeName(), "NUMERIC");
        Assert.assertEquals(table.getKeyColumns().get(0).getName(), "firstName");
        Assert.assertEquals(table.getKeyColumns().get(0).getTypeName(), "TEXT");
        Assert.assertEquals(table.getKeyColumns().get(1).getName(), "lastName");
        Assert.assertEquals(table.getKeyColumns().get(1).getTypeName(), "TEXT");
        Assert.assertEquals(table.getNonKeyColumns().get(0).getName(), "age");
        Assert.assertEquals(table.getNonKeyColumns().get(0).getTypeName(), "INTEGER");
        Assert.assertEquals(table.getNonKeyColumns().get(1).getName(), "long");
        Assert.assertEquals(table.getNonKeyColumns().get(1).getTypeName(), "INTEGER");
        // Test get getTableDefinition
        log.info("verify buildInsertSql");
        String expctedInsertStatement = "INSERT INTO " + tableName +
            "(firstName,lastName,age,bool,byte,short,long,float,double,bytes)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?)";
        String insertStatement = JdbcUtils.buildInsertSql(table);
        Assert.assertEquals(insertStatement, expctedInsertStatement);
        log.info("verify buildUpdateSql");
        String expectedUpdateStatement = "UPDATE " + tableName +
                " SET age=? ,long=?  WHERE firstName=? AND lastName=?";
        String updateStatement = JdbcUtils.buildUpdateSql(table);
        Assert.assertEquals(updateStatement, expectedUpdateStatement);
        log.info("verify buildDeleteSql");
        String expectedDeleteStatement = "DELETE FROM " + tableName +
                " WHERE firstName=? AND lastName=?";
        String deleteStatement = JdbcUtils.buildDeleteSql(table);
        Assert.assertEquals(deleteStatement, expectedDeleteStatement);
    }

}