class DummyClass_159689 {
    @Test
    public void noSchema() throws Exception {
        this.statementUnderTest = new RenameColumnStatement(null, null, TABLE_NAME, COLUMN_NAME, "new_name", "int");

        assertCorrect("rename column table_name.column_name to new_name", DerbyDatabase.class, InformixDatabase.class);
        assertCorrect("alter table table_name alter column column_name rename to new_name", H2Database.class, HsqlDatabase.class);
        assertCorrect("alter table table_name alter column column_name to new_name", FirebirdDatabase.class);
        assertCorrect("alter table table_name change column_name new_name int", MySQLDatabase.class, MariaDBDatabase.class);
        assertCorrect("exec sp_rename '[table_name].[column_name]', 'new_name'", MSSQLDatabase.class);
        assertCorrect("exec sp_rename 'table_name.column_name', 'new_name'", SybaseDatabase.class);
        assertCorrect("alter table [table_name] rename column_name to new_name",SybaseASADatabase.class);
        assertCorrectOnRest("alter table [table_name] rename column [column_name] to [new_name]");
    }

}