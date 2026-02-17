class DummyClass_137128 {
	@Test
	public void getAnnotationsReturnsDirectAnnotations() {
		MethodMetadata metadata = getTagged(WithDirectAnnotation.class);
		assertThat(metadata.getAnnotations().stream().filter(
				MergedAnnotation::isDirectlyPresent).map(
						a -> a.getType().getName())).containsExactlyInAnyOrder(
								Tag.class.getName(),
								DirectAnnotation.class.getName());
	}

}