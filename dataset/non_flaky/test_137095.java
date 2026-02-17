class DummyClass_137095 {
	@Test
	public void getAnnotationsReturnsDirectAnnotations() {
		assertThat(get(WithDirectAnnotations.class).getAnnotations().stream())
			.filteredOn(MergedAnnotation::isDirectlyPresent)
			.extracting(a -> a.getType().getName())
			.containsExactlyInAnyOrder(DirectAnnotation1.class.getName(), DirectAnnotation2.class.getName());
	}

}