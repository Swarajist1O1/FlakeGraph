class DummyClass_162358 {
    @Test
    public void testRender() {
        ClientResponse response = getResponse(BASE_URL + "/test?arg=foo", "text/hml");
        assertEquals(200, response.getStatus());
        
        // Should do testing base on HTML structure if can figure the right library for that, in the meantime ...
        List<String> paras = findmatches(response.getEntity(String.class), "<p>([^<]*)</p>");
        assertEquals("Hello there from myapp (parameter = This is a string)", paras.get(0));
        assertEquals("Query param arg = foo", paras.get(1));
        assertEquals("Component1.prop1 = Component 1 name", paras.get(2));
        assertEquals("Library plugin: Hello from lib plugin - myplugin in application myapp", paras.get(3));
    }

}