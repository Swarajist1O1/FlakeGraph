class DummyClass_137079 {
	@Test
	public void isInterfaceWhenInterfaceReturnsTrue() {
		assertThat(get(TestInterface.class).isInterface()).isTrue();
		assertThat(get(TestAnnotation.class).isInterface()).isTrue();
	}

}