class DummyClass_137094 {
	@Test
	public void getMemberClassNamesWhenHasNoMemberClassesReturnsEmptyArray() {
		assertThat(get(TestClass.class).getMemberClassNames()).isEmpty();
	}

}