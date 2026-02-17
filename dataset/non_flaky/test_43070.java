class DummyClass_43070 {
    @Test
    public void testShowCreateInformationSchemaTable()
    {
        assertQueryFails("SHOW CREATE VIEW information_schema.schemata", "line 1:1: Relation '\\w+.information_schema.schemata' is a table, not a view");
        assertQueryFails("SHOW CREATE MATERIALIZED VIEW information_schema.schemata", "line 1:1: Relation '\\w+.information_schema.schemata' is a table, not a materialized view");

        assertThat((String) computeScalar("SHOW CREATE TABLE information_schema.schemata"))
                .isEqualTo("CREATE TABLE " + getSession().getCatalog().orElseThrow() + ".information_schema.schemata (\n" +
                        "   catalog_name varchar,\n" +
                        "   schema_name varchar\n" +
                        ")");
    }

}