class DummyClass_43021 {
	@Test
	public void testApiVerb() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController", apiDoc.getName());
		Assert.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/produces-one")) {
				Assert.assertEquals(1, apiMethodDoc.getProduces().size());
				Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getProduces().iterator().next());
			}
			if (apiMethodDoc.getPath().contains("/produces-two")) {
				Assert.assertEquals(2, apiMethodDoc.getProduces().size());
				Iterator<String> iterator = apiMethodDoc.getProduces().iterator();
				Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
				Assert.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
			}
			if (apiMethodDoc.getPath().contains("/produces-three")) {
				Assert.assertEquals(1, apiMethodDoc.getProduces().size());
				String produces = apiMethodDoc.getProduces().iterator().next();
				Assert.assertEquals("application/json", produces);
			}
		}
		
		apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController2.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController2", apiDoc.getName());
		Assert.assertEquals(3, apiDoc.getMethods().size());
		for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
			if (apiMethodDoc.getPath().contains("/produces-one")) {
				Assert.assertEquals(1, apiMethodDoc.getProduces().size());
				Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getProduces().iterator().next());
			}
			if (apiMethodDoc.getPath().contains("/produces-two")) {
				Assert.assertEquals(2, apiMethodDoc.getProduces().size());
				Iterator<String> iterator = apiMethodDoc.getProduces().iterator();
				Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
				Assert.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
			}
			if (apiMethodDoc.getPath().contains("/produces-three")) {
				Assert.assertEquals(1, apiMethodDoc.getProduces().size());
				Assert.assertEquals(MediaType.APPLICATION_XML_VALUE, apiMethodDoc.getProduces().iterator().next());
			}
		}
	}

}