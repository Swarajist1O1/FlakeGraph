class DummyClass_43022 {
	@Test
	public void testMergeApiDoc() {
		Set<Class<?>> controllers = new LinkedHashSet<Class<?>>();
		controllers.add(SpringController.class);
		Set<ApiDoc> apiDocs = jsondocScanner.getApiDocs(controllers, MethodDisplay.URI);
		
		ApiDoc apiDoc = apiDocs.iterator().next();
		Assert.assertEquals("A spring controller", apiDoc.getDescription());
		Assert.assertEquals("Spring controller", apiDoc.getName());
		
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if(apiMethodDoc.getPath().contains("/api/string/{name}")) {
				Assert.assertNotNull(apiMethodDoc.getAuth());
				Assert.assertNotNull(apiMethodDoc.getSupportedversions());
				Assert.assertFalse(apiMethodDoc.getApierrors().isEmpty());
				
				Assert.assertEquals("string", apiMethodDoc.getBodyobject().getJsondocType().getOneLineText());
				Assert.assertEquals("string", apiMethodDoc.getResponse().getJsondocType().getOneLineText());
				Assert.assertEquals("/api/string/{name}", apiMethodDoc.getPath().iterator().next());
				Assert.assertEquals("POST", apiMethodDoc.getVerb().iterator().next().name());
				Assert.assertEquals("application/json", apiMethodDoc.getProduces().iterator().next());
				Assert.assertEquals("application/json", apiMethodDoc.getConsumes().iterator().next());
				Assert.assertEquals("201 - Created", apiMethodDoc.getResponsestatuscode());
				
				Set<ApiHeaderDoc> headers = apiMethodDoc.getHeaders();
				ApiHeaderDoc header = headers.iterator().next();
				Assert.assertEquals("header", header.getName());
				Assert.assertEquals("test", header.getAllowedvalues()[0]);
				
				Set<ApiParamDoc> queryparameters = apiMethodDoc.getQueryparameters();
				Assert.assertEquals(3, queryparameters.size());
				Iterator<ApiParamDoc> qpIterator = queryparameters.iterator();
				ApiParamDoc apiParamDoc = qpIterator.next();
				Assert.assertEquals("delete", apiParamDoc.getName());
				Assert.assertEquals("true", apiParamDoc.getRequired());
				Assert.assertEquals(null, apiParamDoc.getDefaultvalue());
				Assert.assertEquals(0, apiParamDoc.getAllowedvalues().length);
				apiParamDoc = qpIterator.next();
				Assert.assertEquals("id", apiParamDoc.getName());
				Assert.assertEquals("true", apiParamDoc.getRequired());
				Assert.assertTrue(apiParamDoc.getDefaultvalue().isEmpty());
				apiParamDoc = qpIterator.next();
				Assert.assertEquals("myquery", apiParamDoc.getName());
				Assert.assertEquals("true", apiParamDoc.getRequired());
				Assert.assertEquals("", apiParamDoc.getDefaultvalue());
				
				Set<ApiParamDoc> pathparameters = apiMethodDoc.getPathparameters();
				Iterator<ApiParamDoc> ppIterator = pathparameters.iterator();
				apiParamDoc = ppIterator.next();
				apiParamDoc = apiMethodDoc.getPathparameters().iterator().next();
				Assert.assertEquals("test", apiParamDoc.getName());
			}
		}
		
	}

}