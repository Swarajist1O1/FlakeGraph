class DummyClass_137112 {
	@Test
	public void getAnnotatedMethodsReturnsMatchingAnnotatedAndMetaAnnotatedMethods() {
		assertThat(get(WithDirectAndMetaAnnotatedMethods.class).getAnnotatedMethods(MetaAnnotation2.class.getName()))
			.extracting(MethodMetadata::getMethodName)
			.containsExactlyInAnyOrder("direct", "meta");
	}

}