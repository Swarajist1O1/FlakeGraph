class DummyClass_159671 {
    @Test
    public void generateSql_autoIncrement() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, "table_name", "column_name", "int", null, new AutoIncrementConstraint("column_name"));

        assertCorrect("alter table table_name add column_name serial", InformixDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int default autoincrement null", SybaseASADatabase.class);
        assertCorrect("alter table [table_name] add [column_name] serial", PostgresDatabase.class);
        assertCorrect("alter table [dbo].[table_name] add [column_name] int identity", MSSQLDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int identity null", SybaseDatabase.class);
        assertCorrect("not supported. fixme!!", SQLiteDatabase.class);
        assertCorrect("alter table table_name add column_name int auto_increment_clause");
    }

}