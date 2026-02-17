class DummyClass_159681 {
    @Test
    public void execute_noSchema() throws Exception {
        this.statementUnderTest = new AddUniqueConstraintStatement(null, null, TABLE_NAME, new ColumnConfig[] { new ColumnConfig().setName(COLUMN_NAME)}, CONSTRAINT_NAME);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", SybaseDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", MSSQLDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", SybaseASADatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", MySQLDatabase.class);
        assertCorrect("alter table adduqtest add constraint unique (coltomakeuq) constraint uq_test", InformixDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", OracleDatabase.class);
        assertCorrect("alter table \"adduqtest\" add constraint uq_test unique (\"coltomakeuq\")", PostgresDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", DerbyDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])");
    }

}