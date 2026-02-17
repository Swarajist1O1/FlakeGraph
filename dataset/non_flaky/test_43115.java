class DummyClass_43115 {
    @Test
    public void testHaving()
    {
        assertQuery("SELECT regionkey, sum(nationkey) FROM nation GROUP BY regionkey HAVING sum(nationkey) = 58", "VALUES (4, 58)");
    }

}