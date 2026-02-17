class DummyClass_137134 {
	@Test // gh-24375
	public void metadataLoadsForNestedAnnotations() {
		AnnotationMetadata annotationMetadata = get(AnnotatedComponent.class);
		assertThat(annotationMetadata.getAnnotationTypes()).containsExactly(EnclosingAnnotation.class.getName());
	}

}