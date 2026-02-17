class DummyClass_91500 {
    @Test
    public void testWithCubeData() throws Exception {
        Driver driver = new Driver();
        Properties info = new Properties();
        info.put("user", "ADMIN");
        info.put("password", "KYLIN");
        Connection conn = driver.connect("jdbc:kylin://localhost:7070/default", info);

        ResultSet catalogs = conn.getMetaData().getCatalogs();
        System.out.println("CATALOGS");
        printResultSetMetaData(catalogs);
        printResultSet(catalogs);

        ResultSet schemas = conn.getMetaData().getSchemas();
        System.out.println("SCHEMAS");
        printResultSetMetaData(schemas);
        printResultSet(schemas);

        ResultSet tables = conn.getMetaData().getTables(null, null, null, null);
        System.out.println("TABLES");
        printResultSetMetaData(tables);
        printResultSet(tables);

        for (int j = 0; j < 3; j++) {
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from test_kylin_fact");

            printResultSetMetaData(resultSet);
            printResultSet(resultSet);

            resultSet.close();
        }

        catalogs.close();
        schemas.close();
        tables.close();
        conn.close();
    }

}