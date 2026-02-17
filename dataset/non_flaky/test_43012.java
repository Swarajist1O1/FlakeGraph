class DummyClass_43012 {
    @Test
    public void testApiVerb() {
	ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController.class), MethodDisplay.URI).iterator().next();
	Assert.assertEquals("SpringController", apiDoc.getName());
	Assert.assertEquals(3, apiDoc.getMethods().size());
	for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
	    if (apiMethodDoc.getPath().contains("/consumes-one")) {
		Assert.assertEquals(1, apiMethodDoc.getConsumes().size());
		Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getConsumes().iterator().next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-two")) {
		Assert.assertEquals(2, apiMethodDoc.getConsumes().size());
		Iterator<String> iterator = apiMethodDoc.getConsumes().iterator();
		Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
		Assert.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-three")) {
		Assert.assertEquals(1, apiMethodDoc.getConsumes().size());
		String consumes = apiMethodDoc.getConsumes().iterator().next();
		Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, consumes);
	    }
	}

	apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController2.class), MethodDisplay.URI).iterator().next();
	Assert.assertEquals("SpringController2", apiDoc.getName());
	Assert.assertEquals(3, apiDoc.getMethods().size());
	for (ApiMethodDoc apiMethodDoc : apiDoc.getMethods()) {
	    if (apiMethodDoc.getPath().contains("/consumes-one")) {
		Assert.assertEquals(1, apiMethodDoc.getConsumes().size());
		Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, apiMethodDoc.getConsumes().iterator().next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-two")) {
		Assert.assertEquals(2, apiMethodDoc.getConsumes().size());
		Iterator<String> iterator = apiMethodDoc.getConsumes().iterator();
		Assert.assertEquals(MediaType.APPLICATION_JSON_VALUE, iterator.next());
		Assert.assertEquals(MediaType.APPLICATION_XML_VALUE, iterator.next());
	    }
	    if (apiMethodDoc.getPath().contains("/consumes-three")) {
		Assert.assertEquals(1, apiMethodDoc.getConsumes().size());
		Assert.assertEquals(MediaType.APPLICATION_XML_VALUE, apiMethodDoc.getConsumes().iterator().next());
	    }
	}
    }

}