class DummyClass_42983 {
	@Test
	public void testApiHeadersOnClass() {
		final ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>>newHashSet(ApiHeadersController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("ApiHeadersController", apiDoc.getName());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/api-headers-controller-method-one")) {
				Assert.assertEquals(2, apiMethodDoc.getHeaders().size());
			}
			if(apiMethodDoc.getPath().contains("/api-headers-controller-method-two")) {
				Assert.assertEquals(3, apiMethodDoc.getHeaders().size());
			}
		}
	}

}