class DummyClass_43091 {
    @Test
    public void testLimit()
    {
        assertEquals(computeActual("SELECT * FROM orders LIMIT 10").getRowCount(), 10);
    }

}