class DummyClass_43023 {
    @Test
    public void getJSONDoc() throws IOException {
        JSONDocScanner jsondocScanner = new Spring3JSONDocScanner();
        JSONDoc jsondoc = jsondocScanner.getJSONDoc(version, basePath, Lists.newArrayList("org.jsondoc.springmvc.controller"), true, MethodDisplay.URI);

        Map<String, Set<ApiObjectDoc>> objects = jsondoc.getObjects();
        for (Set<ApiObjectDoc> values : objects.values()) {
            for (ApiObjectDoc apiObjectDoc : values) {
                System.out.println(apiObjectDoc.getName());
            }
        }

    }

}