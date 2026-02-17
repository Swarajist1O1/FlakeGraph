class DummyClass_137089 {
	@Test
	public void getSuperClassNameWhenHasSuperClassReturnsName() {
		assertThat(get(TestSubclass.class).getSuperClassName()).isEqualTo(TestClass.class.getName());
		assertThat(get(TestClass.class).getSuperClassName()).isEqualTo(Object.class.getName());
	}

}