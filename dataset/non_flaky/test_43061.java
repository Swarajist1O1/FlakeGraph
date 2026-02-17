class DummyClass_43061 {
    @Test
    public void testViewAndMaterializedViewTogether()
    {
        if (!hasBehavior(SUPPORTS_CREATE_MATERIALIZED_VIEW) || !hasBehavior(SUPPORTS_CREATE_VIEW)) {
            return;
        }
        // Validate that it is possible to have views and materialized views defined at the same time and both are operational

        String schemaName = getSession().getSchema().orElseThrow();

        String regularViewName = "test_views_together_normal_" + randomTableSuffix();
        assertUpdate("CREATE VIEW " + regularViewName + " AS SELECT * FROM region");

        String materializedViewName = "test_views_together_materialized_" + randomTableSuffix();
        assertUpdate("CREATE MATERIALIZED VIEW " + materializedViewName + " AS SELECT * FROM nation");

        // both should be accessible via information_schema.views
        // TODO: actually it is not the cased now hence overridable `checkInformationSchemaViewsForMaterializedView`
        assertThat(query("SELECT table_name FROM information_schema.views WHERE table_schema = '" + schemaName + "'"))
                .skippingTypesCheck()
                .containsAll("VALUES '" + regularViewName + "'");
        checkInformationSchemaViewsForMaterializedView(schemaName, materializedViewName);

        // check we can query from both
        assertThat(query("SELECT * FROM " + regularViewName)).containsAll("SELECT * FROM region");
        assertThat(query("SELECT * FROM " + materializedViewName)).containsAll("SELECT * FROM nation");

        assertUpdate("DROP VIEW " + regularViewName);
        assertUpdate("DROP MATERIALIZED VIEW " + materializedViewName);
    }

}