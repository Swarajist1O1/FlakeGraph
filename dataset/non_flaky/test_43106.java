class DummyClass_43106 {
    @Test
    public void testSelectInformationSchemaTables()
    {
        String catalog = getSession().getCatalog().get();
        String schema = getSession().getSchema().get();
        String schemaPattern = schema.replaceAll("^.", "_");

        assertQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = '" + schema + "' AND table_name = 'orders'", "VALUES 'orders'");
        assertQuery("SELECT table_name FROM information_schema.tables WHERE table_schema LIKE '" + schema + "' AND table_name LIKE '%rders'", "VALUES 'orders'");
        assertQuery("SELECT table_name FROM information_schema.tables WHERE table_schema LIKE '" + schemaPattern + "' AND table_name LIKE '%rders'", "VALUES 'orders'");
        assertQuery(
                "SELECT table_name FROM information_schema.tables " +
                        "WHERE table_catalog = '" + catalog + "' AND table_schema LIKE '" + schema + "' AND table_name LIKE '%orders'",
                "VALUES 'orders'");
        assertQuery("SELECT table_name FROM information_schema.tables WHERE table_catalog = 'something_else'", "SELECT '' WHERE false");

        assertQuery(
                "SELECT DISTINCT table_name FROM information_schema.tables WHERE table_schema = 'information_schema' OR rand() = 42 ORDER BY 1",
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