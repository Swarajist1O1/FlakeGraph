class DummyClass_43116 {
    @Test
    public void testJoin()
    {
        assertQuery("SELECT n.name, r.name FROM nation n JOIN region r on n.regionkey = r.regionkey");
    }

}