class DummyClass_43008 {
	@Test
	public void testPath4() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController4.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController4", apiDoc.getName());

		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				boolean allRight =
								input.getPath().contains("/path"); 
				return allRight;
			}

}