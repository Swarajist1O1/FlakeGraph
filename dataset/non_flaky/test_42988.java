class DummyClass_42988 {
	@Test
	public void testPathWithMethodDisplayURI() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(Controller.class), MethodDisplay.URI).iterator().next();

		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				return 
						input.getPath().contains("/path1") && 
						input.getPath().contains("/path2") && 
						input.getDisplayedMethodString().contains("/path1") &&
						input.getDisplayedMethodString().contains("/path2");
			}

}