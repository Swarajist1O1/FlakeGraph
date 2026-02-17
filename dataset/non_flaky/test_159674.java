class DummyClass_159674 {
    @Test
    public void autoIncrement() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, TABLE_NAME, "column_name", "int", null, new AutoIncrementConstraint());

        assertCorrect("ALTER TABLE [dbo].[table_name] ADD [column_name] int auto_increment_clause", MSSQLDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int default autoincrement null", SybaseASADatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int identity null", SybaseDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] serial", PostgresDatabase.class, InformixDatabase.class);
        assertCorrect("not supported. fixme!!", SQLiteDatabase.class);
        assertCorrectOnRest("ALTER TABLE [table_name] ADD [column_name] int auto_increment_clause");
    }

}