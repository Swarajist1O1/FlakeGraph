class DummyClass_137090 {
	@Test
	public void getSuperClassNameWhenHasNoSuperClassReturnsNull() {
		assertThat(get(Object.class).getSuperClassName()).isNull();
		assertThat(get(TestInterface.class).getSuperClassName()).isNull();
		assertThat(get(TestSubInterface.class).getSuperClassName()).isNull();
	}

}