class DummyClass_43069 {
    @Test
    public void testShowCreateInformationSchema()
    {
        assertThat(query("SHOW CREATE SCHEMA information_schema"))
                .skippingTypesCheck()
                .matches(format("VALUES 'CREATE SCHEMA %s.information_schema'", getSession().getCatalog().orElseThrow()));
    }

}