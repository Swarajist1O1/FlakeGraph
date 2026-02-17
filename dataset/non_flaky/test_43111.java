class DummyClass_43111 {
    @Test
    public void testPredicate()
    {
        assertQuery("SELECT name, regionkey FROM nation WHERE nationkey = 10");
        assertQuery("SELECT name, regionkey FROM nation WHERE nationkey BETWEEN 5 AND 15");
        assertQuery("SELECT name, regionkey FROM nation WHERE name = 'EGYPT'");
    }

}