class DummyClass_137105 {
	@Test
	public void hasAnnotationWhenDoesNotMatchDirectOrMetaAnnotationReturnsFalse() {
		assertThat(get(TestClass.class).hasAnnotation(DirectAnnotation1.class.getName())).isFalse();
	}

}