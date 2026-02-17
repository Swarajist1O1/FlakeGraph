class DummyClass_159672 {
    @Test
    public void generateSql_notNull() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, null, "table_name", "column_name", "int", 42, new NotNullConstraint());
        assertCorrect("alter table [table_name] add [column_name] int default 42 not null", SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect("alter table table_name add column_name int default 42 not null", PostgresDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] [int] constraint df_table_name_column_name default 42 not null", MSSQLDatabase.class);
        assertCorrect("alter table table_name add column_name int default 42 not null", MySQLDatabase.class);
        assertCorrect("not supported. fixme!!", SQLiteDatabase.class);
        assertCorrect("ALTER TABLE [table_name] ADD [column_name] int DEFAULT 42 NOT NULL");
    }

}