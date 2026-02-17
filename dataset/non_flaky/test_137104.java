class DummyClass_137104 {
	@Test
	public void hasAnnotationWhenMatchesMetaAnnotationReturnsFalse() {
		assertThat(get(WithMetaAnnotations.class).hasAnnotation(MetaAnnotation1.class.getName())).isFalse();
		assertThat(get(WithMetaAnnotations.class).hasAnnotation(MetaAnnotation2.class.getName())).isFalse();
	}

}