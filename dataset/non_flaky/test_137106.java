class DummyClass_137106 {
	@Test
	public void hasMetaAnnotationWhenMatchesDirectReturnsFalse() {
		assertThat(get(WithDirectAnnotations.class).hasMetaAnnotation(DirectAnnotation1.class.getName())).isFalse();
	}

}