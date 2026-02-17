class DummyClass_91499 {
    @Test
    public void testPreparedStatementWithMockData() throws SQLException {
        Driver driver = new DummyDriver();

        Connection conn = driver.connect("jdbc:kylin://test_url/test_db", null);
        PreparedStatement state = conn.prepareStatement("select * from test_table where id=?");
        state.setInt(1, 10);
        ResultSet resultSet = state.executeQuery();

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