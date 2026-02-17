class DummyClass_159682 {
    @Test
    public void execute_noConstraintName() throws Exception {
        this.statementUnderTest = new AddUniqueConstraintStatement(null, null, TABLE_NAME, new ColumnConfig[] { new ColumnConfig().setName(COLUMN_NAME)}, null);
        assertCorrect("alter table adduqtest add unique (coltomakeuq)", MySQLDatabase.class);
        assertCorrect("alter table adduqtest add constraint unique (coltomakeuq)", InformixDatabase.class);
        assertCorrect("alter table adduqtest add unique (coltomakeuq)", OracleDatabase.class);
        assertCorrect("alter table \"adduqtest\" add unique (\"coltomakeuq\")", PostgresDatabase.class);
        assertCorrect("alter table adduqtest add unique (coltomakeuq)", DerbyDatabase.class);
        assertCorrect("alter table [adduqtest] add unique ([coltomakeuq])", SybaseASADatabase.class, SybaseDatabase.class);
        assertCorrect("alter table [adduqtest] add unique ([coltomakeuq])", MSSQLDatabase.class);

        assertCorrect("alter table [adduqtest] add unique ([coltomakeuq])");
    }

}