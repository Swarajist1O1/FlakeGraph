class DummyClass_43013 {
	@Test
	public void testQueryParam() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController", apiDoc.getName());
		Assert.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one")) {
				Assert.assertEquals(1, apiMethodDoc.getQueryparameters().size());
			}
			if (apiMethodDoc.getPath().contains("/param-two")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
			}
			if (apiMethodDoc.getPath().contains("/param-three")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				Assert.assertEquals("param", param.getName());
				Assert.assertEquals("value", param.getAllowedvalues()[0]);
				ApiParamDoc param2 = iterator.next();
				Assert.assertEquals("param2", param2.getName());
				Assert.assertEquals("value2", param2.getAllowedvalues()[0]);
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController2.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController2", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
			}
			if (apiMethodDoc.getPath().contains("/param-two")) {
				Assert.assertEquals(3, apiMethodDoc.getQueryparameters().size());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController3.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController3", apiDoc.getName());
		Assert.assertEquals(4, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/param-one")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assert.assertEquals("name", queryParam.getName());
				Assert.assertEquals("true", queryParam.getRequired());
				Assert.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assert.assertEquals("", queryParam.getDefaultvalue());
			}
			if (apiMethodDoc.getPath().contains("/param-two")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assert.assertEquals("name", queryParam.getName());
				Assert.assertEquals("false", queryParam.getRequired());
				Assert.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assert.assertEquals("test", queryParam.getDefaultvalue());
			}
			if (apiMethodDoc.getPath().contains("/param-three")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assert.assertEquals("", queryParam.getName());
				Assert.assertEquals("true", queryParam.getRequired());
				Assert.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assert.assertEquals("", queryParam.getDefaultvalue());
			}
			if (apiMethodDoc.getPath().contains("/param-four")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
				Iterator<ApiParamDoc> iterator = apiMethodDoc.getQueryparameters().iterator();
				ApiParamDoc param = iterator.next();
				ApiParamDoc queryParam = iterator.next();
				Assert.assertEquals("value", queryParam.getName());
				Assert.assertEquals("false", queryParam.getRequired());
				Assert.assertEquals("string", queryParam.getJsondocType().getOneLineText());
				Assert.assertEquals("", queryParam.getDefaultvalue());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController4.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController4", apiDoc.getName());
		Assert.assertEquals(2, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/")) {
				Assert.assertEquals(1, apiMethodDoc.getQueryparameters().size());
				ApiParamDoc param = apiMethodDoc.getQueryparameters().iterator().next();
				Assert.assertEquals("name", param.getName());
			}
			if (apiMethodDoc.getPath().contains("/two")) {
				Assert.assertEquals(2, apiMethodDoc.getQueryparameters().size());
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController5.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController5", apiDoc.getName());
		Assert.assertEquals(1, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/")) {
				Assert.assertEquals(1, apiMethodDoc.getQueryparameters().size());
				ApiParamDoc param = apiMethodDoc.getQueryparameters().iterator().next();
				Assert.assertEquals("modelAttributePojo", param.getName());
				Assert.assertEquals("modelattributepojo", param.getJsondocType().getOneLineText());
			}
		}
		
	}

}