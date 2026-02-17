class DummyClass_137107 {
	@Test
	public void hasMetaAnnotationWhenMatchesMetaAnnotationReturnsTrue() {
		assertThat(get(WithMetaAnnotations.class).hasMetaAnnotation(MetaAnnotation1.class.getName())).isTrue();
		assertThat(get(WithMetaAnnotations.class).hasMetaAnnotation(MetaAnnotation2.class.getName())).isTrue();
	}

}