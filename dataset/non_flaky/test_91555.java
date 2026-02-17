class DummyClass_91555 {
    @Test
    public void testFetchValue() {
        Map<String, String> map = new HashMap<>();
        String guess = JdbcHiveInputBase.fetchValue("DB_1", "TB_2", "COL_3", map);

        // not found, return input value
        assertEquals("DB_1.TB_2.COL_3", guess);
        map.put("DB_1.TB_2.COL_3", "Db_1.Tb_2.Col_3");

        guess = JdbcHiveInputBase.fetchValue("DB_1", "TB_2", "COL_3", map);
        // found, return cached value
        assertEquals("Db_1.Tb_2.Col_3", guess);
    }

}