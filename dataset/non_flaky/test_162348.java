class DummyClass_162348 {
    @Test
    public void testQuery() {
        List<Literal> literals = SQueryUtil.selectLiteralVar("x", "SELECT ?x WHERE {test:i1 ?p ?x}", ssource, app.getPrefixes());
        TestUtil.testArray(literals, new Literal[]{
                ResourceFactory.createPlainLiteral("name"),
                ResourceFactory.createPlainLiteral("rdfs label"),
                ResourceFactory.createPlainLiteral("Alt label"),
                ResourceFactory.createPlainLiteral("Pref label"),
        });
        
        List<Resource> resources = SQueryUtil.selectResourceVar("x", "SELECT ?x WHERE {test:i1 ?p ?x}", ssource, app.getPrefixes());
        TestUtil.testArray(resources, new Resource[]{
                ResourceFactory.createResource(TEST_NS + "Sample")
        });
    }

}