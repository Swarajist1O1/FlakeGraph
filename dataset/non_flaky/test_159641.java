class DummyClass_159641 {
    @Test
    public void testRunUpdateOnOldChangelogTableFormat() throws Exception {
        assumeNotNull(this.getDatabase());
        Liquibase liquibase = createLiquibase(completeChangeLog);
        clearDatabase();

        String nullableKeyword = database.requiresExplicitNullForColumns() ? " NULL" : "";

        String sql = "CREATE TABLE " +
                database.escapeTableName(
                        database.getDefaultCatalogName(), database.getDefaultSchemaName(), "DATABASECHANGELOG"
                ) +
                " (id varchar(150) NOT NULL, " +
                "author VARCHAR(150) NOT NULL, " +
                "filename VARCHAR(255) NOT NULL, " +
                "dateExecuted " +
                DataTypeFactory.getInstance().fromDescription(
                        "datetime", database
                ).toDatabaseDataType(database) + " NOT NULL, " +
                "md5sum VARCHAR(32)" + nullableKeyword + ", " +
                "description VARCHAR(255)" + nullableKeyword + ", " +
                "comments VARCHAR(255)" + nullableKeyword + ", " +
                "tag VARCHAR(255)" + nullableKeyword + ", " +
                "liquibase VARCHAR(10)" + nullableKeyword + ", " +
                "PRIMARY KEY (id, author, filename))";
        Scope.getCurrentScope().getLog(getClass()).info(LogType.WRITE_SQL, sql);

        Connection conn = ((JdbcConnection) database.getConnection()).getUnderlyingConnection();
        boolean savedAcSetting = conn.getAutoCommit();
        conn.setAutoCommit(false);
        conn.createStatement().execute(sql);
        conn.commit();
        conn.setAutoCommit(savedAcSetting);

        liquibase = createLiquibase(completeChangeLog);
        liquibase.setChangeLogParameter( "loginuser", getUsername());
        liquibase.update(this.contexts);

    }

}