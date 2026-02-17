class DummyClass_43010 {
	@Test
	public void testPath6() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController6.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController6", apiDoc.getName());

		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				return input.getPath().contains("/api/widget/frame");
			}

}