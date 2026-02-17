class DummyClass_137122 {
	@Test
	public void isStatusWhenStaticReturnsTrue() {
		assertThat(getTagged(WithStaticMethod.class).isStatic()).isTrue();
	}

}