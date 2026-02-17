class DummyClass_42993 {
    @Test
    public void getJSONDoc() throws IOException {
    	JSONDocScanner jsondocScanner = new DefaultJSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, Lists.newArrayList("org.jsondoc.core.util"), true, MethodDisplay.URI);
        assertEquals(1, jsondoc.getApis().size());

        int countApis = 0;
        for (String string : jsondoc.getApis().keySet()) {
            countApis += jsondoc.getApis().get(string).size();
        }
        assertEquals(4, countApis);

        assertEquals(3, jsondoc.getObjects().size());
        
        int countFlows = 0;
        for (String string : jsondoc.getFlows().keySet()) {
        	countFlows += jsondoc.getFlows().get(string).size();
        }
        assertEquals(2, countFlows);

        int countObjects = 0;
        for (String string : jsondoc.getObjects().keySet()) {
            countObjects += jsondoc.getObjects().get(string).size();
        }
        assertEquals(10, countObjects);

        Set<ApiVerb> apiVerbs = getAllTestedApiVerbs(jsondoc);
        assertEquals(ApiVerb.values().length, apiVerbs.size());

        log.debug(objectMapper.writeValueAsString(jsondoc));
    }

}