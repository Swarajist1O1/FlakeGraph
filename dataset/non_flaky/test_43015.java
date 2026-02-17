class DummyClass_43015 {
	@Test
	public void testApiHeadersOnClass() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringApiHeadersController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringApiHeadersController", apiDoc.getName());
		Assert.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/spring-api-headers-controller-method-one")) {
				Assert.assertEquals(2, apiMethodDoc.getHeaders().size());
			}
			if (apiMethodDoc.getPath().contains("/spring-api-headers-controller-method-two")) {
				Assert.assertEquals(3, apiMethodDoc.getHeaders().size());
			}
			if (apiMethodDoc.getPath().contains("/spring-api-headers-controller-method-three")) {
				Assert.assertEquals(4, apiMethodDoc.getHeaders().size());
				Iterator<ApiHeaderDoc> headers = apiMethodDoc.getHeaders().iterator();
				ApiHeaderDoc h1 = headers.next();
				ApiHeaderDoc h2 = headers.next();
				ApiHeaderDoc h4 = headers.next();
				Assert.assertEquals("h4", h4.getName());
				ApiHeaderDoc h5 = headers.next();
				Assert.assertEquals("h5", h5.getName());
			}
		}
	}

}