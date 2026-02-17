class DummyClass_91556 {
    @Test
    public void testQuoteIdentifier() {
        String guess = JdbcHiveInputBase.quoteIdentifier("Tbl1.Col1", SourceDialect.MYSQL);
        assertEquals("`Tbl1`.`Col1`", guess);
        guess = JdbcHiveInputBase.quoteIdentifier("Tbl1.Col1", SourceDialect.SQL_SERVER);
        assertEquals("[Tbl1].[Col1]", guess);
    }

}