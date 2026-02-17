class DummyClass_42987 {
	@Test
	public void testApiVisibility() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(Controller.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals(ApiVisibility.PUBLIC, apiDoc.getVisibility());
		Assert.assertEquals(ApiStage.BETA, apiDoc.getStage());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/inherit")) {
				Assert.assertEquals(ApiVisibility.PUBLIC, apiMethodDoc.getVisibility());
				Assert.assertEquals(ApiStage.BETA, apiMethodDoc.getStage());
			}
			if(apiMethodDoc.getPath().contains("/override")) {
				Assert.assertEquals(ApiVisibility.PRIVATE, apiMethodDoc.getVisibility());
				Assert.assertEquals(ApiStage.GA, apiMethodDoc.getStage());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(Controller2.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals(ApiVisibility.UNDEFINED, apiDoc.getVisibility());
		Assert.assertEquals(ApiStage.UNDEFINED, apiDoc.getStage());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/only-method")) {
				Assert.assertEquals(ApiVisibility.PRIVATE, apiMethodDoc.getVisibility());
				Assert.assertEquals(ApiStage.DEPRECATED, apiMethodDoc.getStage());
			}
		}
		
	}

}