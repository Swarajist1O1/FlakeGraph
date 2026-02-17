class DummyClass_91496 {
    @Test
    public void testStatementWithQuestionMask() throws SQLException {
        Driver driver = new DummyDriver();

        Connection conn = driver.connect("jdbc:kylin://test_url/test_db", null);
        Statement state = conn.createStatement();
        ResultSet resultSet = state.executeQuery("select * from test_table where url not in ('http://a.b.com/?a=b')");
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