class DummyClass_137111 {
	@Test
	public void hasAnnotatedMethodsWhenDoesNotMatchAnyAnnotationReturnsFalse() {
		assertThat(get(WithAnnotatedMethod.class).hasAnnotatedMethods(MetaAnnotation2.class.getName())).isFalse();
		assertThat(get(WithNonAnnotatedMethod.class).hasAnnotatedMethods(DirectAnnotation1.class.getName())).isFalse();
	}

}