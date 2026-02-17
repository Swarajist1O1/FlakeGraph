class DummyClass_43059 {
    @Test
    public void testMaterializedView()
    {
        if (!hasBehavior(SUPPORTS_CREATE_MATERIALIZED_VIEW)) {
            assertQueryFails("CREATE MATERIALIZED VIEW nation_mv AS SELECT * FROM nation", "This connector does not support creating materialized views");
            return;
        }

        QualifiedObjectName view = new QualifiedObjectName(
                getSession().getCatalog().orElseThrow(),
                getSession().getSchema().orElseThrow(),
                "test_materialized_view_" + randomTableSuffix());
        QualifiedObjectName otherView = new QualifiedObjectName(
                getSession().getCatalog().orElseThrow(),
                "other_schema",
                "test_materialized_view_" + randomTableSuffix());
        QualifiedObjectName viewWithComment = new QualifiedObjectName(
                getSession().getCatalog().orElseThrow(),
                getSession().getSchema().orElseThrow(),
                "test_materialized_view_with_comment_" + randomTableSuffix());

        createTestingMaterializedView(view, Optional.empty());
        createTestingMaterializedView(otherView, Optional.of("sarcastic comment"));
        createTestingMaterializedView(viewWithComment, Optional.of("mv_comment"));

        // verify comment
        MaterializedResult materializedRows = computeActual("SHOW CREATE MATERIALIZED VIEW " + viewWithComment);
        assertThat((String) materializedRows.getOnlyValue()).contains("COMMENT 'mv_comment'");
        assertThat(query(
                "SELECT table_name, comment FROM system.metadata.table_comments " +
                        "WHERE catalog_name = '" + view.getCatalogName() + "' AND " +
                        "schema_name = '" + view.getSchemaName() + "'"))
                .skippingTypesCheck()
                .containsAll("VALUES ('" + view.getObjectName() + "', null), ('" + viewWithComment.getObjectName() + "', 'mv_comment')");

        // reading
        assertThat(query("SELECT * FROM " + view))
                .skippingTypesCheck()
                .matches("SELECT * FROM nation");
        assertThat(query("SELECT * FROM " + viewWithComment))
                .skippingTypesCheck()
                .matches("SELECT * FROM nation");

        // table listing
        assertThat(query("SHOW TABLES"))
                .skippingTypesCheck()
                .containsAll("VALUES '" + view.getObjectName() + "'");
        // information_schema.tables without table_name filter
        assertThat(query(
                "SELECT table_name, table_type FROM information_schema.tables " +
                        "WHERE table_schema = '" + view.getSchemaName() + "'"))
                .skippingTypesCheck()
                .containsAll("VALUES ('" + view.getObjectName() + "', 'BASE TABLE')"); // TODO table_type should probably be "* VIEW"
        // information_schema.tables with table_name filter
        assertQuery(
                "SELECT table_name, table_type FROM information_schema.tables " +
                        "WHERE table_schema = '" + view.getSchemaName() + "' and table_name = '" + view.getObjectName() + "'",
                "VALUES ('" + view.getObjectName() + "', 'BASE TABLE')");

        // system.jdbc.tables without filter
        assertThat(query("SELECT table_schem, table_name, table_type FROM system.jdbc.tables"))
                .skippingTypesCheck()
                .containsAll("VALUES ('" + view.getSchemaName() + "', '" + view.getObjectName() + "', 'TABLE')");

        // system.jdbc.tables with table prefix filter
        assertQuery(
                "SELECT table_schem, table_name, table_type " +
                        "FROM system.jdbc.tables " +
                        "WHERE table_cat = '" + view.getCatalogName() + "' AND " +
                        "table_schem = '" + view.getSchemaName() + "' AND " +
                        "table_name = '" + view.getObjectName() + "'",
                "VALUES ('" + view.getSchemaName() + "', '" + view.getObjectName() + "', 'TABLE')");

        // column listing
        assertThat(query("SHOW COLUMNS FROM " + view.getObjectName()))
                .projected(0) // column types can very between connectors
                .skippingTypesCheck()
                .matches("VALUES 'nationkey', 'name', 'regionkey', 'comment'");

        assertThat(query("DESCRIBE " + view.getObjectName()))
                .projected(0) // column types can very between connectors
                .skippingTypesCheck()
                .matches("VALUES 'nationkey', 'name', 'regionkey', 'comment'");

        // information_schema.columns without table_name filter
        assertThat(query(
                "SELECT table_name, column_name " +
                        "FROM information_schema.columns " +
                        "WHERE table_schema = '" + view.getSchemaName() + "'"))
                .skippingTypesCheck()
                .containsAll(
                        "SELECT * FROM (VALUES '" + view.getObjectName() + "') " +
                                "CROSS JOIN UNNEST(ARRAY['nationkey', 'name', 'regionkey', 'comment'])");

        // information_schema.columns with table_name filter
        assertThat(query(
                "SELECT table_name, column_name " +
                        "FROM information_schema.columns " +
                        "WHERE table_schema = '" + view.getSchemaName() + "' and table_name = '" + view.getObjectName() + "'"))
                .skippingTypesCheck()
                .containsAll(
                        "SELECT * FROM (VALUES '" + view.getObjectName() + "') " +
                                "CROSS JOIN UNNEST(ARRAY['nationkey', 'name', 'regionkey', 'comment'])");

        // view-specific listings
        checkInformationSchemaViewsForMaterializedView(view.getSchemaName(), view.getObjectName());

        // system.jdbc.columns without filter
        @Language("SQL") String expectedValues = "VALUES ('" + view.getSchemaName() + "', '" + view.getObjectName() + "', 'nationkey'), " +
                "('" + view.getSchemaName() + "', '" + view.getObjectName() + "', 'name'), " +
                "('" + view.getSchemaName() + "', '" + view.getObjectName() + "', 'regionkey'), " +
                "('" + view.getSchemaName() + "', '" + view.getObjectName() + "', 'comment')";
        assertThat(query(
                "SELECT table_schem, table_name, column_name FROM system.jdbc.columns"))
                .skippingTypesCheck()
                .containsAll(expectedValues);

        // system.jdbc.columns with schema filter
        assertThat(query(
                "SELECT table_schem, table_name, column_name " +
                        "FROM system.jdbc.columns " +
                        "WHERE table_schem LIKE '%" + view.getSchemaName() + "%'"))
                .skippingTypesCheck()
                .containsAll(expectedValues);

        // system.jdbc.columns with table filter
        assertQuery(
                "SELECT table_schem, table_name, column_name " +
                        "FROM system.jdbc.columns " +
                        "WHERE table_name LIKE '%" + view.getObjectName() + "%'",
                expectedValues);

        // details
        assertThat(((String) computeScalar("SHOW CREATE MATERIALIZED VIEW " + view.getObjectName())))
                .matches("(?s)" +
                        "CREATE MATERIALIZED VIEW \\Q" + view + "\\E" +
                        ".* AS\n" +
                        "SELECT \\*\n" +
                        "FROM\n" +
                        "  nation");

        // we only want to test filtering materialized views in different schemas,
        // `viewWithComment` is in the same schema as `view` so it is not needed
        assertUpdate("DROP MATERIALIZED VIEW " + viewWithComment);

        // test filtering materialized views in system metadata table
        assertThat(query(listMaterializedViewsSql("catalog_name = '" + view.getCatalogName() + "'")))
                .skippingTypesCheck()
                .containsAll(getTestingMaterializedViewsResultRows(view, otherView));

        assertThat(query(
                listMaterializedViewsSql(
                        "catalog_name = '" + otherView.getCatalogName() + "'",
                        "schema_name = '" + otherView.getSchemaName() + "'")))
                .skippingTypesCheck()
                .containsAll(getTestingMaterializedViewsResultRow(otherView, "sarcastic comment"));

        assertThat(query(
                listMaterializedViewsSql(
                        "catalog_name = '" + view.getCatalogName() + "'",
                        "schema_name = '" + view.getSchemaName() + "'",
                        "name = '" + view.getObjectName() + "'")))
                .skippingTypesCheck()
                .containsAll(getTestingMaterializedViewsResultRow(view, ""));

        assertThat(query(
                listMaterializedViewsSql("schema_name LIKE '%" + view.getSchemaName() + "%'")))
                .skippingTypesCheck()
                .containsAll(getTestingMaterializedViewsResultRow(view, ""));

        assertThat(query(
                listMaterializedViewsSql("name LIKE '%" + view.getObjectName() + "%'")))
                .skippingTypesCheck()
                .containsAll(getTestingMaterializedViewsResultRow(view, ""));

        // verify write in transaction
        if (!hasBehavior(SUPPORTS_MULTI_STATEMENT_WRITES)) {
            assertThatThrownBy(() -> inTransaction(session -> computeActual(session, "REFRESH MATERIALIZED VIEW " + view)))
                    .hasMessageMatching("Catalog only supports writes using autocommit: \\w+");
        }

        assertUpdate("DROP MATERIALIZED VIEW " + view);
        assertUpdate("DROP MATERIALIZED VIEW " + otherView);

        assertQueryReturnsEmptyResult(listMaterializedViewsSql("name = '" + view.getObjectName() + "'"));
        assertQueryReturnsEmptyResult(listMaterializedViewsSql("name = '" + otherView.getObjectName() + "'"));
        assertQueryReturnsEmptyResult(listMaterializedViewsSql("name = '" + viewWithComment.getObjectName() + "'"));
    }

}