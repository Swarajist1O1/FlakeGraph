class DummyClass_162350 {
    @Test
    public void testUpdate() {
        assertTrue( ssource.isUpdateable() );
        String update = "" +
        		"PREFIX test: <http://www.epimorphics.com/vocabs/test/> \n" +
        		"DELETE {?x test:string ?s}\n" +
        		"INSERT {?x test:string 'new string'}\n" +
        		"WHERE {?x test:num 42}";
        ssource.update( UpdateFactory.create(update) );
        
        WNode v = getNode("test:test").getPropertyValue("test:string");
        assertNotNull(v);
        assertEquals("new string", v.getLabel());
    }

}