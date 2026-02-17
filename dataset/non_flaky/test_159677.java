class DummyClass_159677 {
    @Test
    public void generateSql_foreignKey() throws Exception {
        this.statementUnderTest = new AddColumnStatement(null, "table_name", "column_name", "int", null, new PrimaryKeyConstraint(), new ForeignKeyConstraint("fk_test_fk", "table_name(column_name)"));

        assertCorrect(new String[] {"alter table [table_name] add [column_name] int not null primary key", "alter table [table_name] add constraint [fk_test_fk] foreign key ([column_name]) references [table_name]([column_name])"}, HsqlDatabase.class);
        assertCorrect(new String[] {"alter table [table_name] add [column_name] int primary key not null", "alter table [table_name] add constraint [fk_test_fk] foreign key ([column_name]) references [table_name]([column_name])"}, SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect(new String[] {"alter table [dbo].[table_name] add [column_name] int not null primary key", "alter table [dbo].[table_name] add constraint [fk_test_fk] foreign key ([column_name]) references [dbo].[table_name]([column_name])"}, MSSQLDatabase.class);
        assertCorrect(new String[] {"alter table table_name add column_name int not null primary key", "alter table [table_name] add constraint [fk_test_fk] foreign key ([column_name]) references [table_name]([column_name])"}, PostgresDatabase.class);
        assertCorrect(new String[] {"alter table `table_name` add `column_name` int not null primary key", "alter table [table_name] add constraint [fk_test_fk] foreign key ([column_name]) references [table_name]([column_name])"}, MySQLDatabase.class);
        assertCorrect(new String[] {"ALTER TABLE [table_name] ADD [column_name] int PRIMARY KEY NOT NULL", "alter table [table_name] add constraint  foreign key ([column_name]) references [table_name]([column_name]) constraint [fk_test_fk]"}, InformixDatabase.class);
        assertCorrect(new String[] {"ALTER TABLE [table_name] ADD [column_name] int PRIMARY KEY NOT NULL", "alter table [table_name] add constraint [fk_test_fk] foreign key ([column_name]) references [table_name]([column_name])"});
    }

}