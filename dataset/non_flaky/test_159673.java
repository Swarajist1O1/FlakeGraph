class DummyClass_159673 {
    @Test
    public void fullNoConstraints() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, null, "table_name", "column_name", "int", 42);


        assertCorrect("alter table [table_name] add [column_name] int default 42 null", SybaseDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int constraint df_table_name_column_name default 42", MSSQLDatabase.class);
//        assertCorrect("alter table [table_name] add [column_name] integer default 42", SQLiteDatabase.class);
        assertCorrect("not supported. fixme!!", SQLiteDatabase.class);
        assertCorrect("alter table table_name add column_name int default 42", PostgresDatabase.class, InformixDatabase.class, OracleDatabase.class, DerbyDatabase.class, HsqlDatabase.class, DB2Database.class, H2Database.class, FirebirdDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int default 42 null", SybaseASADatabase.class);
        assertCorrect("alter table table_name add column_name int default 42 null", MySQLDatabase.class, MariaDBDatabase.class);
        assertCorrectOnRest("ALTER TABLE [table_name] ADD [column_name] int DEFAULT 42");
    }

}