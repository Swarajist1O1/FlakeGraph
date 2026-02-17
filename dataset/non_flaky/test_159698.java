class DummyClass_159698 {
    @Test
    public void generateSql() throws Exception {
        this.statementUnderTest = new SelectFromDatabaseChangeLogLockStatement("LOCKED");
        assertCorrect("select [locked] from [databasechangeloglock] where [id]=1", MSSQLDatabase.class, SybaseDatabase.class);
        assertCorrect("select [locked] from [databasechangeloglock] where [id]=1", SybaseASADatabase.class);
        assertCorrect("select [locked] from [databasechangeloglock] where [id]=1 for update", OracleDatabase.class);
        assertCorrectOnRest("select [locked] from [databasechangeloglock] where [id]=1");
    }

}