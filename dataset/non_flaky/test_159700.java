class DummyClass_159700 {
    @Test
    public void generateSql_multicolumn() throws Exception {
        this.statementUnderTest = new SelectFromDatabaseChangeLogLockStatement("LOCKED", "LOCKEDBY");
        assertCorrect("select [locked],[lockedby] from [databasechangeloglock] where [id]=1", MSSQLDatabase.class, SybaseDatabase.class);
        assertCorrect("select [locked],[lockedby] from [databasechangeloglock] where [id]=1", MSSQLDatabase.class, SybaseASADatabase.class);
        assertCorrect("select [locked],[lockedby] from [databasechangeloglock] where [id]=1 for update", OracleDatabase.class);
        assertCorrectOnRest("select [locked],[lockedby] from [databasechangeloglock] where [id]=1");
    }

}