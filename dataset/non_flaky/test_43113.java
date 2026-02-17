class DummyClass_43113 {
    @Test
    public void testTopN()
    {
        assertQuery("SELECT regionkey FROM nation ORDER BY name LIMIT 3");
    }

}