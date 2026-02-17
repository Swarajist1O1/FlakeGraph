class DummyClass_43007 {
	@Test
	public void testPath3() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController3.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController3", apiDoc.getName());

		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				boolean allRight =
								input.getPath().contains("/path1/path3") && 
								input.getPath().contains("/path1/path4") && 
								input.getPath().contains("/path2/path3") && 
								input.getPath().contains("/path2/path4");     
				return allRight;
			}

}