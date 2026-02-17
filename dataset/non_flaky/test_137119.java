class DummyClass_137119 {
	@Test
	public void getReturnTypeReturnsReturnType() {
		assertThat(getTagged(WithMethod.class).getReturnTypeName()).isEqualTo(
				String.class.getName());
	}

}