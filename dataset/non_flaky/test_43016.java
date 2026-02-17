class DummyClass_43016 {
	@Test
	public void testPathVariable() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one/{id}/{string}")) {
				Assert.assertEquals(2, apiMethodDoc.getPathparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getPathparameters().iterator();
				ApiParamDoc id = iterator.next();
				Assert.assertEquals("", id.getName());
				Assert.assertEquals("long", id.getJsondocType().getOneLineText());
				ApiParamDoc name = iterator.next();
				Assert.assertEquals("name", name.getName());
				Assert.assertEquals("string", name.getJsondocType().getOneLineText());
			}
			
			if (apiMethodDoc.getPath().contains("/param-one/{id}/{string}/{test}")) {
				Assert.assertEquals(3, apiMethodDoc.getPathparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getPathparameters().iterator();
				ApiParamDoc id = iterator.next();
				Assert.assertEquals("id", id.getName());
				Assert.assertEquals("long", id.getJsondocType().getOneLineText());
				ApiParamDoc name = iterator.next();
				Assert.assertEquals("name", name.getName());
				Assert.assertEquals("string", name.getJsondocType().getOneLineText());
				ApiParamDoc test = iterator.next();
				Assert.assertEquals("", test.getName());
				Assert.assertEquals("long", test.getJsondocType().getOneLineText());
			}
		}
		
	}

}