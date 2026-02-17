class DummyClass_43019 {
	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController", apiDoc.getName());
		Assert.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/response-one")) {
				Assert.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			if (apiMethodDoc.getPath().contains("/response-two")) {
				Assert.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
			if (apiMethodDoc.getPath().contains("/response-three")) {
				Assert.assertEquals("map[string, integer]", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
			}
		}
	}

}