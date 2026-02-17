class DummyClass_43099 {
    @Test
    public void testShowTables()
    {
        MaterializedResult actualTables = computeActual("SHOW TABLES").toTestTypes();
        MaterializedResult expectedTables = MaterializedResult.resultBuilder(getSession(), VARCHAR)
                .row("orders")
                .build();
        assertContains(actualTables, expectedTables);
    }

}