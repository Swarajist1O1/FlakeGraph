class DummyClass_137093 {
	@Test
	public void getMemberClassNamesWhenHasMemberClassesReturnsNames() {
		assertThat(get(TestMemberClass.class).getMemberClassNames()).containsExactlyInAnyOrder(
			TestMemberClassInnerClass.class.getName(), TestMemberClassInnerInterface.class.getName());
	}

}