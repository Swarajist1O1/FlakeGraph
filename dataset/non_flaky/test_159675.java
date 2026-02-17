class DummyClass_159675 {
    @Test
    public void notNull() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, null, TABLE_NAME, "column_name", "int", 42, new NotNullConstraint());

        assertCorrect("ALTER TABLE [table_name] ADD [column_name] int DEFAULT 42 NOT NULL", SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect("alter table table_name add column_name int default 42 not null", InformixDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int constraint df_table_name_column_name default 42 not null", MSSQLDatabase.class);
        assertCorrect("alter table table_name add column_name int default 42 not null", OracleDatabase.class, DerbyDatabase.class, HsqlDatabase.class, DB2Database.class, H2Database.class, FirebirdDatabase.class);
        assertCorrect("not supported. fixme!!", SQLiteDatabase.class);
        assertCorrectOnRest("ALTER TABLE [table_name] ADD [column_name] int default 42 not null");
    }

}