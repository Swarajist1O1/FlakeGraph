class DummyClass_43112 {
    @Test
    public void testLimit()
    {
        assertQuery("SELECT name FROM region LIMIT 5");
    }

}