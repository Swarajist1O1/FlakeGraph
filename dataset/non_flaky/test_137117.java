class DummyClass_137117 {
	@Test
	public void getMethodNameReturnsMethodName() {
		assertThat(getTagged(WithMethod.class).getMethodName()).isEqualTo("test");
	}

}