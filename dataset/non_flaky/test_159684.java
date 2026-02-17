class DummyClass_159684 {
    @Test
    public void execute_withTablespace() throws Exception {
        statementUnderTest = new AddUniqueConstraintStatement(null, null, TABLE_NAME, new ColumnConfig[] { new ColumnConfig().setName(COLUMN_NAME)}, CONSTRAINT_NAME).setTablespace(TABLESPACE_NAME);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq]) on liquibase2", MSSQLDatabase.class);
        assertCorrect("alter table adduqtest add constraint unique (coltomakeuq) constraint uq_test", InformixDatabase.class);
        assertCorrect("alter table \"adduqtest\" add constraint uq_test unique (\"coltomakeuq\") USING INDEX TABLESPACE " + TABLESPACE_NAME, PostgresDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", MySQLDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", MariaDBDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", DerbyDatabase.class, HsqlDatabase.class, DB2Database.class, H2Database.class, FirebirdDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", Db2zDatabase.class);
        assertCorrectOnRest("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq]) USING INDEX TABLESPACE " + TABLESPACE_NAME);
    }

}