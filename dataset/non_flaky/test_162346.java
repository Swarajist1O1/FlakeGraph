class DummyClass_162346 {
    @Test
    public void testAll() {
        assertEquals(5, numMatches("'Somerset'"));
        
        // These options would require per-predicate text index
//        assertEquals(2, numMatches("(rdfs:label 'Somerset')"));
//        assertEquals(3, numMatches("(eg:label   'Somerset')"));
    }

}