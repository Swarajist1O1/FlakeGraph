class DummyClass_137080 {
	@Test
	public void isInterfaceWhenNotInterfaceReturnsFalse() {
		assertThat(get(TestClass.class).isInterface()).isFalse();
	}

}