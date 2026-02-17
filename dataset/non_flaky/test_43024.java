class DummyClass_43024 {
    @Test
    public void findsNestedObject() throws Exception {
        JSONDocScanner jsondocScanner = new Spring3JSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, Lists.newArrayList("org.jsondoc.springmvc.controller"), true, MethodDisplay.URI);

        Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
        for (Set<ApiObjectDoc> values : objects.values()) {
            assertContainsDoc(values, "NestedObject1");
        }
    }

}