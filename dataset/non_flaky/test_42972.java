class DummyClass_42972 {
	@Test
	public void testApiErrorsDoc() throws Exception {

		final ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>>newHashSet(Test3Controller.class),
				MethodDisplay.URI).iterator().next();

		final Set<ApiMethodDoc> methods = apiDoc.getMethods();
		final ApiMethodDoc apiMethodDoc = methods.iterator().next();
		final List<ApiErrorDoc> apiErrors = apiMethodDoc.getApierrors();

		Assert.assertEquals(1, methods.size());
		Assert.assertEquals(3, apiErrors.size());
		Assert.assertEquals("1000", apiErrors.get(0).getCode());
		Assert.assertEquals("method-level annotation should be applied",
				"A test error #1", apiErrors.get(0).getDescription());
		Assert.assertEquals("2000", apiErrors.get(1).getCode());
		Assert.assertEquals("400", apiErrors.get(2).getCode());

	}

}