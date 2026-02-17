class DummyClass_159661 {
    @Test
    public void testContextsWithHyphensWorkInFormattedSql() throws Exception {
        assumeNotNull(this.getDatabase());
        Liquibase liquibase = createLiquibase("changelogs/common/sqlstyle/formatted.changelog.sql");
        liquibase.update("hyphen-context-using-sql,camelCaseContextUsingSql");

        SnapshotGeneratorFactory tableSnapshotGenerator = SnapshotGeneratorFactory.getInstance();
        assertNotNull(tableSnapshotGenerator.has(new Table().setName("hyphen_context"), database));
        assertNotNull(tableSnapshotGenerator.has(new Table().setName("camel_context"), database));
        assertNotNull(tableSnapshotGenerator.has(new Table().setName("bar_id"), database));
        assertNotNull(tableSnapshotGenerator.has(new Table().setName("foo_id"), database));
    }

}