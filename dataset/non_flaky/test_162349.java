class DummyClass_162349 {
    @Test
    public void testStreamableSelect() {
        String query = PrefixUtils.expandQuery("SELECT ?x WHERE {test:i1 a ?x}", app.getPrefixes());
        ClosableResultSet results = ssource.streamableSelect(query);
        try {
            assertTrue(results.hasNext());
            assertEquals(ResourceFactory.createResource(TEST_NS + "Sample"), results.next().getResource("x"));
            assertFalse(results.hasNext());
        } finally {
            results.close();
        }
    }

}