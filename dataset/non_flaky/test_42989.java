class DummyClass_42989 {
	@Test
	public void testPathWithMethodDisplayMethod() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(Controller.class), MethodDisplay.METHOD).iterator().next();
		
		boolean allRight = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			@Override
			public boolean apply(ApiMethodDoc input) {
				return 
						input.getPath().contains("/path1") && 
						input.getPath().contains("/path2") && 
						input.getDisplayedMethodString().contains("path") &&
						!input.getDisplayedMethodString().contains("/path1");
			}

}