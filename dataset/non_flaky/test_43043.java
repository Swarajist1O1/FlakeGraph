class DummyClass_43043 {
    @Test
    public void testCharVarcharComparison()
    {
        skipTestUnless(hasBehavior(SUPPORTS_CREATE_TABLE));

        try (TestTable table = new TestTable(
                getQueryRunner()::execute,
                "test_char_varchar",
                "(k, v) AS VALUES" +
                        "   (-1, CAST(NULL AS char(3))), " +
                        "   (3, CAST('   ' AS char(3)))," +
                        "   (6, CAST('x  ' AS char(3)))")) {
            // varchar of length shorter than column's length
            assertQuery(
                    "SELECT k, v FROM " + table.getName() + " WHERE v = CAST('  ' AS varchar(2))",
                    // The value is included because both sides of the comparison are coerced to char(3)
                    "VALUES (3, '   ')");

            // varchar of length longer than column's length
            assertQuery(
                    "SELECT k, v FROM " + table.getName() + " WHERE v = CAST('  ' AS varchar(4))",
                    // The value is included because both sides of the comparison are coerced to char(4)
                    "VALUES (3, '   ')");

            // value that's not all-spaces
            assertQuery(
                    "SELECT k, v FROM " + table.getName() + " WHERE v = CAST('x ' AS varchar(2))",
                    // The value is included because both sides of the comparison are coerced to char(3)
                    "VALUES (6, 'x  ')");
        }
    }

}