class DummyClass_43060 {
    @Test
    public void testRenameMaterializedView()
    {
        skipTestUnless(hasBehavior(SUPPORTS_CREATE_MATERIALIZED_VIEW));

        String schema = "rename_mv_test";
        Session session = Session.builder(getSession())
                .setSchema(schema)
                .build();

        QualifiedObjectName originalMaterializedView = new QualifiedObjectName(
                session.getCatalog().orElseThrow(),
                session.getSchema().orElseThrow(),
                "test_materialized_view_rename_" + randomTableSuffix());

        createTestingMaterializedView(originalMaterializedView, Optional.empty());

        String renamedMaterializedView = "test_materialized_view_rename_new_" + randomTableSuffix();
        if (!hasBehavior(SUPPORTS_RENAME_MATERIALIZED_VIEW)) {
            assertQueryFails(session, "ALTER MATERIALIZED VIEW " + originalMaterializedView + " RENAME TO " + renamedMaterializedView, "This connector does not support renaming materialized views");
            assertUpdate(session, "DROP MATERIALIZED VIEW " + originalMaterializedView);
            return;
        }

        // simple rename
        assertUpdate(session, "ALTER MATERIALIZED VIEW " + originalMaterializedView + " RENAME TO " + renamedMaterializedView);
        assertTestingMaterializedViewQuery(schema, renamedMaterializedView);
        // verify new name in the system.metadata.materialized_views
        assertQuery(session, "SELECT catalog_name, schema_name FROM system.metadata.materialized_views WHERE name = '" + renamedMaterializedView + "'",
                format("VALUES ('%s', '%s')", originalMaterializedView.getCatalogName(), originalMaterializedView.getSchemaName()));
        assertQueryReturnsEmptyResult(session, listMaterializedViewsSql("name = '" + originalMaterializedView.getObjectName() + "'"));

        // rename with IF EXISTS on existing materialized view
        String testExistsMaterializedViewName = "test_materialized_view_rename_exists_" + randomTableSuffix();
        assertUpdate(session, "ALTER MATERIALIZED VIEW IF EXISTS " + renamedMaterializedView + " RENAME TO " + testExistsMaterializedViewName);
        assertTestingMaterializedViewQuery(schema, testExistsMaterializedViewName);

        // rename with upper-case, not delimited identifier
        String uppercaseName = "TEST_MATERIALIZED_VIEW_RENAME_UPPERCASE_" + randomTableSuffix();
        assertUpdate(session, "ALTER MATERIALIZED VIEW " + testExistsMaterializedViewName + " RENAME TO " + uppercaseName);
        assertTestingMaterializedViewQuery(schema, uppercaseName.toLowerCase(ENGLISH)); // Ensure select allows for lower-case, not delimited identifier

        String otherSchema = "rename_mv_other_schema";
        assertUpdate(format("CREATE SCHEMA IF NOT EXISTS %s", otherSchema));
        if (hasBehavior(SUPPORTS_RENAME_MATERIALIZED_VIEW_ACROSS_SCHEMAS)) {
            assertUpdate(session, "ALTER MATERIALIZED VIEW " + uppercaseName + " RENAME TO " + otherSchema + "." + originalMaterializedView.getObjectName());
            assertTestingMaterializedViewQuery(otherSchema, originalMaterializedView.getObjectName());

            assertUpdate(session, "DROP MATERIALIZED VIEW " + otherSchema + "." + originalMaterializedView.getObjectName());
        }
        else {
            assertQueryFails(
                    session,
                    "ALTER MATERIALIZED VIEW " + uppercaseName + " RENAME TO " + otherSchema + "." + originalMaterializedView.getObjectName(),
                    "Materialized View rename across schemas is not supported");
            assertUpdate(session, "DROP MATERIALIZED VIEW " + uppercaseName);
        }

        assertFalse(getQueryRunner().tableExists(session, originalMaterializedView.toString()));
        assertFalse(getQueryRunner().tableExists(session, renamedMaterializedView));
        assertFalse(getQueryRunner().tableExists(session, testExistsMaterializedViewName));

        // rename with IF EXISTS on NOT existing materialized view
        assertUpdate(session, "ALTER TABLE IF EXISTS " + originalMaterializedView + " RENAME TO " + renamedMaterializedView);
        assertQueryReturnsEmptyResult(session, listMaterializedViewsSql("name = '" + originalMaterializedView.getObjectName() + "'"));
        assertQueryReturnsEmptyResult(session, listMaterializedViewsSql("name = '" + renamedMaterializedView + "'"));
    }

}