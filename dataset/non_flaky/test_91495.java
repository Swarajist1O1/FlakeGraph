class DummyClass_91495 {
    @Test
    public void testStatementWithMockData() throws SQLException {
        Driver driver = new DummyDriver();

        Connection conn = driver.connect("jdbc:kylin://test_url/test_db", null);

        ResultSet tables = conn.getMetaData().getTables(null, null, null, null);
        while (tables.next()) {
            for (int i = 0; i < 4; i++) {
                assertEquals("dummy", tables.getString(i + 1));
            }
            for (int i = 4; i < 10; i++) {
                assertEquals(null, tables.getString(i + 1));
            }
        }

        Statement state = conn.createStatement();
        ResultSet resultSet = state.executeQuery("select * from test_table");

        ResultSetMetaData metadata = resultSet.getMetaData();
        assertEquals(12, metadata.getColumnType(1));
        assertEquals("varchar", metadata.getColumnTypeName(1));
        assertEquals(1, metadata.isNullable(1));

        while (resultSet.next()) {
            assertEquals("foo", resultSet.getString(1));
            assertEquals("bar", resultSet.getString(2));
            assertEquals("tool", resultSet.getString(3));
        }

        resultSet.close();
        state.close();
        conn.close();
    }

}