class DummyClass_137101 {
	@Test
	public void getAnnotationTypesReturnsDirectAnnotations() {
		AnnotationMetadata metadata = get(WithDirectAnnotations.class);
		assertThat(metadata.getAnnotationTypes()).containsExactlyInAnyOrder(
				DirectAnnotation1.class.getName(), DirectAnnotation2.class.getName());
	}

}