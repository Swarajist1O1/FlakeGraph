class DummyClass_137091 {
	@Test
	public void getInterfaceNamesWhenHasInterfacesReturnsNames() {
		assertThat(get(TestSubclass.class).getInterfaceNames()).containsExactlyInAnyOrder(TestInterface.class.getName());
		assertThat(get(TestSubInterface.class).getInterfaceNames()).containsExactlyInAnyOrder(TestInterface.class.getName());
	}

}