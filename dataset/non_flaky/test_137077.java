class DummyClass_137077 {
	@Test
	public void verifyToString() throws Exception {
		assertThat(get(TestClass.class).toString()).isEqualTo(TestClass.class.getName());
	}

}