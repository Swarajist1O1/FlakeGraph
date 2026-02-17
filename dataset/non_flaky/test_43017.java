class DummyClass_43017 {
	@Test
	public void testPathVariableWithJSONDoc() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController2.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController2", apiDoc.getName());
		Assert.assertEquals(1, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one/{id}/{string}")) {
				Assert.assertEquals(2, apiMethodDoc.getPathparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getPathparameters().iterator();
				ApiParamDoc id = iterator.next();
				Assert.assertEquals("", id.getName());
				Assert.assertEquals("long", id.getJsondocType().getOneLineText());
				Assert.assertEquals("description for id", id.getDescription());
				ApiParamDoc name = iterator.next();
				Assert.assertEquals("name", name.getName());
				Assert.assertEquals("string", name.getJsondocType().getOneLineText());
			}
		}
		
	}

}