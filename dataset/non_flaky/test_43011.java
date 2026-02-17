class DummyClass_43011 {
	@Test
	public void testPathWithMethodDisplayMethod() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController5.class), MethodDisplay.METHOD).iterator().next();
		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				boolean allRight = input.getPath().contains("/path") && input.getPath().contains("/path2") && input.getDisplayedMethodString().contains("none");
				return allRight;
			}

}