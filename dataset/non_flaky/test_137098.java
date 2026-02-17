class DummyClass_137098 {
	@Test
	public void isAnnotatedWhenDoesNotMatchDirectOrMetaAnnotationReturnsFalse() {
		assertThat(get(TestClass.class).isAnnotated(DirectAnnotation1.class.getName())).isFalse();
	}

}