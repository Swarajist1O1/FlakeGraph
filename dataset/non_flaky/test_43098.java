class DummyClass_43098 {
    @Test
    public void testShowSchemas()
    {
        MaterializedResult actualSchemas = computeActual("SHOW SCHEMAS").toTestTypes();

        MaterializedResult.Builder resultBuilder = MaterializedResult.resultBuilder(getSession(), VARCHAR)
                .row(getSession().getSchema().orElse("tpch"));

        assertContains(actualSchemas, resultBuilder.build());
    }

}