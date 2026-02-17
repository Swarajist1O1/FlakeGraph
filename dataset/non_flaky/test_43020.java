class DummyClass_43020 {
	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringApiVerbController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringApiVerbController", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/api-verb/spring-api-verb-controller-method-one")) {
				Assert.assertEquals(1, apiMethodDoc.getVerb().size());
				Assert.assertEquals(ApiVerb.GET, apiMethodDoc.getVerb().iterator().next());
			}
			if (apiMethodDoc.getPath().contains("/api-verb/spring-api-verb-controller-method-two")) {
				Assert.assertEquals(2, apiMethodDoc.getVerb().size());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringApiVerbController2.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringApiVerbController2", apiDoc.getName());
		Assert.assertEquals(1, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/api-verb-2/spring-api-verb-controller-method-one")) {
				Assert.assertEquals(2, apiMethodDoc.getVerb().size());
			}
		}
		
	}

}