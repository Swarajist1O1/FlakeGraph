class DummyClass_159685 {
    @Test
    public void execute_withDefferedAndDisabled() throws Exception {
        statementUnderTest = new AddUniqueConstraintStatement(null, null, TABLE_NAME, new ColumnConfig[] { new ColumnConfig().setName(COLUMN_NAME)}, CONSTRAINT_NAME).setDeferrable(true).setInitiallyDeferred(true).setDisabled(true);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", SybaseDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", MSSQLDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", SybaseASADatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", MySQLDatabase.class);
        assertCorrect("alter table adduqtest add constraint unique (coltomakeuq) constraint uq_test", InformixDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq) DEFERRABLE INITIALLY " +
                "DEFERRED DISABLE", OracleDatabase.class);
        assertCorrect("ALTER TABLE \"adduqtest\" ADD CONSTRAINT uq_test unique (\"coltomakeuq\") DEFERRABLE INITIALLY" +
                " DEFERRED", PostgresDatabase.class);
        assertCorrect("alter table adduqtest add constraint uq_test unique (coltomakeuq)", DerbyDatabase.class);
        assertCorrect("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])");
    }

}