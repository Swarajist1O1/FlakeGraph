class DummyClass_91497 {
    @Test
    public void testDateAndTimeStampWithMockData() throws SQLException {
        Driver driver = new DummyDriver();

        Connection conn = driver.connect("jdbc:kylin://test_url/test_db", null);
        PreparedStatement state = conn.prepareStatement("select * from test_table where id=?");
        state.setInt(1, 10);
        ResultSet resultSet = state.executeQuery();

        ResultSetMetaData metadata = resultSet.getMetaData();
        assertEquals("date", metadata.getColumnTypeName(4));
        assertEquals("timestamp", metadata.getColumnTypeName(5));

        while (resultSet.next()) {
            assertEquals("2019-04-27", resultSet.getString(4));
            assertEquals("2019-04-27 17:30:03", resultSet.getString(5));
        }

        resultSet.close();
        state.close();
        conn.close();
    }

}