class DummyClass_43018 {
	@Test
	public void testBodyOne() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/body-one")) {
				Assert.assertNotNull(apiMethodDoc.getBodyobject());
				Assert.assertEquals("string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
			}
			if (apiMethodDoc.getPath().contains("/body-two")) {
				Assert.assertNotNull(apiMethodDoc.getBodyobject());
				Assert.assertEquals("body", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
			}
		}
	}

}