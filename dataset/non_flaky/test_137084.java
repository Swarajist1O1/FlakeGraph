class DummyClass_137084 {
	@Test
	public void isFinalWhenNonFinalReturnsFalse() {
		assertThat(get(TestClass.class).isFinal()).isFalse();
	}

}