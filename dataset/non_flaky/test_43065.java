class DummyClass_43065 {
    @Test
    public void testTableSampleWithFiltering()
    {
        MaterializedResult emptySample = computeActual("SELECT DISTINCT orderkey, orderdate FROM orders TABLESAMPLE SYSTEM (99) WHERE orderkey BETWEEN 0 AND 0");
        MaterializedResult halfSample = computeActual("SELECT DISTINCT orderkey, orderdate FROM orders TABLESAMPLE SYSTEM (50) WHERE orderkey BETWEEN 0 AND 9999999999");
        MaterializedResult all = computeActual("SELECT orderkey, orderdate FROM orders");

        assertEquals(emptySample.getMaterializedRows().size(), 0);
        // Assertions need to be loose here because SYSTEM sampling random selects data on split boundaries. In this case either all the data will be selected, or
        // none of it. Sampling with a 100% ratio is ignored, so that also cannot be used to guarantee results.
        assertTrue(all.getMaterializedRows().size() >= halfSample.getMaterializedRows().size());
    }

}