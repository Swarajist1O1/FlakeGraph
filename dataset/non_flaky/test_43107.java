class DummyClass_43107 {
    @Test
    public void testSelectInformationSchemaColumns()
    {
        String catalog = getSession().getCatalog().get();
        String schema = getSession().getSchema().get();
        String schemaPattern = schema.replaceAll(".$", "_");

        @Language("SQL") String ordersTableWithColumns = "VALUES " +
                "('orders', 'orderkey'), " +
                "('orders', 'custkey'), " +
                "('orders', 'orderstatus'), " +
                "('orders', 'totalprice'), " +
                "('orders', 'orderdate'), " +
                "('orders', 'orderpriority'), " +
                "('orders', 'clerk'), " +
                "('orders', 'shippriority'), " +
                "('orders', 'comment')";

        assertQuery("SELECT table_schema FROM information_schema.columns WHERE table_schema = '" + schema + "' GROUP BY table_schema", "VALUES '" + schema + "'");
        assertQuery("SELECT table_name FROM information_schema.columns WHERE table_name = 'orders' GROUP BY table_name", "VALUES 'orders'");
        assertQuery("SELECT table_name, column_name FROM information_schema.columns WHERE table_schema = '" + schema + "' AND table_name = 'orders'", ordersTableWithColumns);
        assertQuery("SELECT table_name, column_name FROM information_schema.columns WHERE table_schema = '" + schema + "' AND table_name LIKE '%rders'", ordersTableWithColumns);
        assertQuery("SELECT table_name, column_name FROM information_schema.columns WHERE table_schema LIKE '" + schemaPattern + "' AND table_name LIKE '_rder_'", ordersTableWithColumns);
        assertQuery(
                "SELECT table_name, column_name FROM information_schema.columns " +
                        "WHERE table_catalog = '" + catalog + "' AND table_schema = '" + schema + "' AND table_name LIKE '%orders%'",
                ordersTableWithColumns);

        assertQuerySucceeds("SELECT * FROM information_schema.columns");
        assertQuery("SELECT DISTINCT table_name, column_name FROM information_schema.columns WHERE table_name LIKE '_rders'", ordersTableWithColumns);
        assertQuerySucceeds("SELECT * FROM information_schema.columns WHERE table_catalog = '" + catalog + "'");
        assertQuerySucceeds("SELECT * FROM information_schema.columns WHERE table_catalog = '" + catalog + "' AND table_schema = '" + schema + "'");
        assertQuery("SELECT table_name, column_name FROM information_schema.columns WHERE table_catalog = '" + catalog + "' AND table_schema = '" + schema + "' AND table_name LIKE '_rders'", ordersTableWithColumns);
        assertQuerySucceeds("SELECT * FROM information_schema.columns WHERE table_catalog = '" + catalog + "' AND table_name LIKE '%'");
        assertQuery("SELECT column_name FROM information_schema.columns WHERE table_catalog = 'something_else'", "SELECT '' WHERE false");

        assertQuery(
                "SELECT DISTINCT table_name FROM information_schema.columns WHERE table_schema = 'information_schema' OR rand() = 42 ORDER BY 1",
                "VALUES " +
                        "('applicable_roles'), " +
                        "('columns'), " +
                        "('enabled_roles'), " +
                        "('role_authorization_descriptors'), " +
                        "('roles'), " +
                        "('schemata'), " +
                        "('table_privileges'), " +
                        "('tables'), " +
                        "('views')");
    }

}