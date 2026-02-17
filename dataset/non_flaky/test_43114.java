class DummyClass_43114 {
    @Test
    public void testAggregation()
    {
        assertQuery("SELECT sum(regionkey) FROM nation");
        assertQuery("SELECT sum(nationkey) FROM nation GROUP BY regionkey");
    }

}