class DummyClass_137075 {
	@Test
	public void verifyEquals() throws Exception {
		AnnotationMetadata testClass1 = get(TestClass.class);
		AnnotationMetadata testClass2 = get(TestClass.class);
		AnnotationMetadata testMemberClass1 = get(TestMemberClass.class);
		AnnotationMetadata testMemberClass2 = get(TestMemberClass.class);

		assertThat(testClass1.equals(null)).isFalse();

		assertThat(testClass1.equals(testClass1)).isTrue();
		assertThat(testClass2.equals(testClass2)).isTrue();
		assertThat(testClass1.equals(testClass2)).isTrue();
		assertThat(testClass2.equals(testClass1)).isTrue();

		assertThat(testMemberClass1.equals(testMemberClass1)).isTrue();
		assertThat(testMemberClass2.equals(testMemberClass2)).isTrue();
		assertThat(testMemberClass1.equals(testMemberClass2)).isTrue();
		assertThat(testMemberClass2.equals(testMemberClass1)).isTrue();

		assertThat(testClass1.equals(testMemberClass1)).isFalse();
		assertThat(testMemberClass1.equals(testClass1)).isFalse();
	}

}