class DummyClass_137108 {
	@Test
	public void hasMetaAnnotationWhenDoesNotMatchDirectOrMetaAnnotationReturnsFalse() {
		assertThat(get(TestClass.class).hasMetaAnnotation(MetaAnnotation1.class.getName())).isFalse();
	}

}