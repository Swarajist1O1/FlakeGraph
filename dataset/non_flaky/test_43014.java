class DummyClass_43014 {
    @Test
    public void testGetMapping() {
        ApiDoc
            apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(RequestMappingController.class), MethodDisplay.URI).iterator().next();
        Assert.assertEquals("RequestMappingController", apiDoc.getName());

        boolean getMethodPresent = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
            @Override
            public boolean apply(ApiMethodDoc input) {
                return input.getMethod().equals("get");
            }

}