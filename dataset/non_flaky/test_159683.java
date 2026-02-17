class DummyClass_159683 {
    @Test
    public void execute_withSchema() throws Exception {
        statementUnderTest = new AddUniqueConstraintStatement(
                DatabaseTestContext.ALT_CATALOG,
                DatabaseTestContext.ALT_SCHEMA,
                TABLE_NAME,
                new ColumnConfig[]
                        {new ColumnConfig().setName(COLUMN_NAME)},
                CONSTRAINT_NAME
        );

        assertCorrect("ALTER TABLE liquibasec.adduqtest ADD CONSTRAINT uq_test UNIQUE (coltomakeuq)", MySQLDatabase
                .class);
        /*
         * In Informix, this test case is actually impossible. While it is allowed to cross-select data from
          * different databases (using the database:schema.table notation), it is not allowed to send DDL to a
          * different database (even if the database is on the same instance). So, even as the following
          * statement is semantically false, it is syntactically correct.
         */
        assertCorrect("ALTER TABLE liquibasec:liquibaseb.adduqtest ADD CONSTRAINT UNIQUE (coltomakeuq) CONSTRAINT " +
                "uq_test", InformixDatabase.class);

        assertCorrect("alter table liquibasec.adduqtest add constraint uq_test unique (coltomakeuq)", OracleDatabase.class);
        assertCorrect("alter table liquibaseb.\"adduqtest\" add constraint uq_test unique (\"coltomakeuq\")", PostgresDatabase.class);
        assertCorrect("alter table liquibasec.adduqtest add constraint uq_test unique (coltomakeuq)", DerbyDatabase
                .class);
        assertCorrect("alter table [liquibaseb].[adduqtest] add constraint [uq_test] unique ([coltomakeuq])",
                SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect("alter table [liquibasec].[liquibaseb].[adduqtest] add constraint [uq_test] unique " +
                "([coltomakeuq])", MSSQLDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", FirebirdDatabase.class);

        assertCorrect("alter table [liquibaseb].[adduqtest] add constraint [uq_test] unique ([coltomakeuq])", HsqlDatabase.class);
        assertCorrect("alter table \"liquibasec\".[adduqtest] add constraint [uq_test] unique ([coltomakeuq])", DB2Database.class, Db2zDatabase.class);
        assertCorrect("alter table [liquibaseb].[adduqtest] add constraint [uq_test] unique ([coltomakeuq])", H2Database.class);
        assertCorrectOnRest("alter table [liquibasec].[adduqtest] add constraint [uq_test] unique ([coltomakeuq])");

    }

}