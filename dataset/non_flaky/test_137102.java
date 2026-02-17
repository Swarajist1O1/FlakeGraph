class DummyClass_137102 {
	@Test
	public void getMetaAnnotationTypesReturnsMetaAnnotations() {
		AnnotationMetadata metadata = get(WithMetaAnnotations.class);
		assertThat(metadata.getMetaAnnotationTypes(MetaAnnotationRoot.class.getName()))
			.containsExactlyInAnyOrder(MetaAnnotation1.class.getName(), MetaAnnotation2.class.getName());
	}

}