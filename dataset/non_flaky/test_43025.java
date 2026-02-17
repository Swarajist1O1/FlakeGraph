class DummyClass_43025 {
    @Test
    public void findsDeeplyNestedObjects() throws Exception {
        JSONDocScanner jsondocScanner = new Spring3JSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, Lists.newArrayList("org.jsondoc.springmvc.controller"), true, MethodDisplay.URI);

        Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
        for (Set<ApiObjectDoc> values : objects.values()) {
            assertContainsDoc(values, "NestedObject2");
            assertContainsDoc(values, "NestedObject3");
        }
    }

}