class DummyClass_91501 {
    @Test
    public void testPreparedStatementWithCubeData() throws SQLException {
        Driver driver = new Driver();
        Properties info = new Properties();
        info.put("user", "ADMIN");
        info.put("password", "KYLIN");
        Connection conn = driver.connect("jdbc:kylin://localhost:7070/default", info);

        PreparedStatement state = conn
                .prepareStatement("select cal_dt, count(*) from test_kylin_fact where seller_id=? group by cal_dt");
        state.setLong(1, 10000001);
        ResultSet resultSet = state.executeQuery();

        printResultSetMetaData(resultSet);
        printResultSet(resultSet);

        resultSet.close();
        state.close();
        conn.close();
    }

}