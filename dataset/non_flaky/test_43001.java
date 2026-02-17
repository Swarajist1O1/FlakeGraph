class DummyClass_43001 {
	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/status-one")) {
				Assert.assertEquals("201 - Created", apiMethodDoc.getResponsestatuscode());
			}
			if (apiMethodDoc.getPath().contains("/status-two")) {
				Assert.assertEquals("200 - OK", apiMethodDoc.getResponsestatuscode());
			}
		}
	}

}