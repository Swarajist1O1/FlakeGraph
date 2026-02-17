class DummyClass_43125 {
    @Test
    public void testCreateSchema()
    {
        String schemaName = "test_schema_create_" + randomTableSuffix();
        if (!hasBehavior(SUPPORTS_CREATE_SCHEMA)) {
            assertQueryFails("CREATE SCHEMA " + schemaName, "This connector does not support creating schemas");
            return;
        }

        assertUpdate("CREATE SCHEMA " + schemaName);
        assertThat(query("SHOW SCHEMAS"))
                .skippingTypesCheck()
                .containsAll(format("VALUES '%s', '%s'", getSession().getSchema().orElseThrow(), schemaName));
        assertUpdate("DROP SCHEMA " + schemaName);
    }

}