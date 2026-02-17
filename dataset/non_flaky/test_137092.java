class DummyClass_137092 {
	@Test
	public void getInterfaceNamesWhenHasNoInterfacesReturnsEmptyArray() {
		assertThat(get(TestClass.class).getInterfaceNames()).isEmpty();
	}

}