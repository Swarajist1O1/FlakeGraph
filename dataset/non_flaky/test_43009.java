class DummyClass_43009 {
	@Test
	public void testPath5() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController5.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController5", apiDoc.getName());
		
		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				boolean allRight = input.getPath().contains("/path") && input.getPath().contains("/path2");
				return allRight;
			}

}