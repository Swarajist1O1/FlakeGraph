class DummyClass_43072 {
    @Test
    public void testWriteNotAllowedInTransaction()
    {
        skipTestUnless(!hasBehavior(SUPPORTS_MULTI_STATEMENT_WRITES));

        assertWriteNotAllowedInTransaction(SUPPORTS_CREATE_SCHEMA, "CREATE SCHEMA write_not_allowed");
        assertWriteNotAllowedInTransaction(SUPPORTS_CREATE_TABLE, "CREATE TABLE write_not_allowed (x int)");
        assertWriteNotAllowedInTransaction(SUPPORTS_CREATE_TABLE, "DROP TABLE region");
        assertWriteNotAllowedInTransaction(SUPPORTS_CREATE_TABLE_WITH_DATA, "CREATE TABLE write_not_allowed AS SELECT * FROM region");
        assertWriteNotAllowedInTransaction(SUPPORTS_CREATE_VIEW, "CREATE VIEW write_not_allowed AS SELECT * FROM region");
        assertWriteNotAllowedInTransaction(SUPPORTS_CREATE_MATERIALIZED_VIEW, "CREATE MATERIALIZED VIEW write_not_allowed AS SELECT * FROM region");
        assertWriteNotAllowedInTransaction(SUPPORTS_RENAME_TABLE, "ALTER TABLE region RENAME TO region_name");
        assertWriteNotAllowedInTransaction(SUPPORTS_INSERT, "INSERT INTO region (regionkey) VALUES (123)");
        assertWriteNotAllowedInTransaction(SUPPORTS_DELETE, "DELETE FROM region WHERE regionkey = 123");

        // REFRESH MATERIALIZED VIEW is tested in testMaterializedView
    }

}