class DummyClass_162347 {
    @Test
    public void testWrapper() {
        // Labels
        assertEquals("Pref label", getNode("test:i1").getLabel());
        assertEquals("Alt label",  getNode("test:i2").getLabel());
        assertEquals("rdfs label", getNode("test:i3").getLabel());
        assertEquals("name",       getNode("test:i4").getLabel());
        
        WNode node = getNode("test:i5");
        assertEquals("en label", node.getLabel("en"));
        assertEquals("plain label", node.getLabel("cy"));
        
        assertTrue(node.isResource());
        assertTrue(node.isURIResource());
        assertFalse(node.isLiteral());
        assertEquals("http://www.epimorphics.com/vocabs/test/i5", node.getURI());
        
        DatasetGraph dsg = source.constructViews("?uri skos:altLabel ?rdfs_label", TEST_NS + "i1", TEST_NS + "i2");
        checkLabel(dsg, "i1", "Alt label");
        checkLabel(dsg, "i2", "Alt label");
        
        // General accessors
        WNode test = getNode("test:test");
        WNode v = test.getPropertyValue("test:num");
        assertNotNull(v);
        assertTrue(v.isLiteral());
        assertTrue(v.isNumber());
        assertEquals(42, v.asInt());
        
        v = test.getPropertyValue("test:float");
        assertNotNull(v);
        assertTrue(v.isLiteral());
        assertTrue(v.isNumber());
        assertEquals(3.14, v.asFloat(), 0.001);
        
        v = test.getPropertyValue("test:string");
        assertNotNull(v);
        assertEquals("a string", v.getLabel());
        
        List<WNode> values = test.listPropertyValues("test:resource");
        assertEquals(2, values.size());
        TestUtil.testArray(values, new WNode[]{ getNode("test:i1"), getNode("test:i2")});
        
        // Lists
        WNode list = test.getPropertyValue("test:list");
        assertNotNull(list);
        assertTrue(list.isList());
        List<WNode> elts = list.asList();
        assertEquals(3,  elts.size());
        assertEquals(1, elts.get(0).asInt());
        assertEquals(3, elts.get(1).asInt());
        assertEquals(5, elts.get(2).asInt());
        
        // Connections
        checkConnections( getNode("test:c").listInLinks("test:p"), new String[]{"test:a", "test:b"} );
        checkConnections( getNode("test:d").listInLinks("test:p"), new String[]{"test:c"} );
        checkConnections( getNode("test:c").listInLinks("test:q"), new String[]{"test:f"} );

        checkConnections( getNode("test:c").connectedNodes("test:p / test:p"), new String[]{"test:e"} );
        
        List<PropertyValue> connections = getNode("test:c").listInLinks();
        assertEquals(2, connections.size());
        assertEquals(getNode("test:p"), connections.get(0).getProp());
        checkConnections( connections.get(0).getValues(), new String[]{"test:a", "test:b"});
        assertEquals(getNode("test:q"), connections.get(1).getProp());
        checkConnections( connections.get(1).getValues(), new String[]{"test:f"});
        
        // Text search
        List<WNode> matches = source.search("aa");
        assertEquals(1, matches.size());
        checkConnections(matches, new String[]{"test:a"});

        matches = source.search("label");
        assertEquals(4, matches.size());
        checkConnections(matches, new String[]{"test:i1", "test:i2", "test:i3", "test:i5"});
        
        matches = source.search("label", 2);
        assertEquals(2, matches.size());
        
        matches = source.search("pref");
        assertEquals(1, matches.size());
        checkConnections(matches, new String[]{"test:i1"});
    }

}