class DummyClass_91546 {
    @Test
    public void testValidateSql() throws Exception {
        explorer.validateSQL("select 1");
        validateSQLInvalidEx.expect(Exception.class);
        explorer.validateSQL("select");
    }

}