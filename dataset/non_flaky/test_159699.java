class DummyClass_159699 {
    @Test
    public void generateSql_count() throws Exception {
        this.statementUnderTest = new SelectFromDatabaseChangeLogLockStatement(new ColumnConfig().setName("COUNT(*)", true));
        assertCorrect("select count(*) from [databasechangeloglock] where [id]=1", MSSQLDatabase.class, SybaseDatabase.class);
        assertCorrect("select count(*) from [databasechangeloglock] where [id]=1", MSSQLDatabase.class, SybaseASADatabase.class);
        assertCorrect("select count(*) from [databasechangeloglock] where [id]=1 for update", OracleDatabase.class);
        assertCorrectOnRest("select count(*) from [databasechangeloglock] where [id]=1");
    }

}