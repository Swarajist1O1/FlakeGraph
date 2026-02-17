class DummyClass_43006 {
	@Test
	public void testPath2() {
		ApiDoc apiDoc = jsondocScanner.getApiDocs(Sets.<Class<?>> newHashSet(SpringController2.class), MethodDisplay.URI).iterator().next();
		Assert.assertEquals("SpringController2", apiDoc.getName());

		boolean none = FluentIterable.from(apiDoc.getMethods()).anyMatch(new Predicate<ApiMethodDoc>() {
			
			@Override
			public boolean apply(ApiMethodDoc input) {
				System.out.println(input.getPath());
				return input.getPath().contains("/");
			}

}