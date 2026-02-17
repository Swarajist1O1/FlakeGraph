class DummyClass_137078 {
	@Test
	public void getClassNameReturnsClassName() {
		assertThat(get(TestClass.class).getClassName()).isEqualTo(TestClass.class.getName());
	}

}