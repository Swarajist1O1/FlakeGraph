class DummyClass_137076 {
	@Test
	public void verifyHashCode() throws Exception {
		AnnotationMetadata testClass1 = get(TestClass.class);
		AnnotationMetadata testClass2 = get(TestClass.class);
		AnnotationMetadata testMemberClass1 = get(TestMemberClass.class);
		AnnotationMetadata testMemberClass2 = get(TestMemberClass.class);

		assertThat(testClass1).hasSameHashCodeAs(testClass2);
		assertThat(testMemberClass1).hasSameHashCodeAs(testMemberClass2);

		assertThat(testClass1).doesNotHaveSameHashCodeAs(testMemberClass1);
	}

}