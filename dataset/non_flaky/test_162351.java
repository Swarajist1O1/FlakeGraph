class DummyClass_162351 {
    @Ignore @Test
    public void testRemoveSource() {
        setup();
        
        assertTrue(source.isUpdateable());
        
        DatasetAccessor accessor = source.getAccessor();
        for (int i = 0; i < 2; i++) {
            Model m = ModelFactory.createDefaultModel();
            m.createResource(TEST + "i" + i)
                .addProperty(RDFS.label, "In graph " + i);
            accessor.putModel(TEST + "g" + i, m);
        }
        
        checkLabels(new String[]{"In graph 0", "In graph 1"});

        String update = "" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
                "WITH <http://localhost/test/def#g1> \n" +
                "DELETE {?x rdfs:label ?l} \n" +
                "INSERT {?x rdfs:label 'new string'} \n" +
                "WHERE {?x rdfs:label ?l}";
        source.update( UpdateFactory.create(update) );

        checkLabels(new String[]{"In graph 0", "new string"});

        // clean up
        for (int i = 0; i < 2; i++) {
            accessor.deleteModel(TEST + "g" + i);
        }
        app.shutdown();
    }

}