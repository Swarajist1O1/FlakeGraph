class DummyClass_159676 {
    @Test
    public void generateSql_primaryKey() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, "table_name", "column_name", "int", null, new PrimaryKeyConstraint());

        assertCorrect("alter table [table_name] add [column_name] int not null primary key", HsqlDatabase.class);
        assertCorrect("alter table [table_name] add [column_name] int primary key not null", SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect("alter table [dbo].[table_name] add [column_name] int not null primary key", MSSQLDatabase.class);
        assertCorrect("alter table table_name add column_name int not null primary key", PostgresDatabase.class);
        assertCorrect("alter table `table_name` add `column_name` int not null primary key", MySQLDatabase.class);
        assertCorrect("ALTER TABLE [table_name] ADD [column_name] int PRIMARY KEY NOT NULL");
    }

}