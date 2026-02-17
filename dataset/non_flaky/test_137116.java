class DummyClass_137116 {
	@Test
	public void verifyToString() throws Exception {
		assertThat(getTagged(WithMethod.class).toString())
			.endsWith(WithMethod.class.getName() + ".test()");

		assertThat(getTagged(WithMethodWithOneArgument.class).toString())
			.endsWith(WithMethodWithOneArgument.class.getName() + ".test(java.lang.String)");

		assertThat(getTagged(WithMethodWithTwoArguments.class).toString())
			.endsWith(WithMethodWithTwoArguments.class.getName() + ".test(java.lang.String,java.lang.Integer)");
	}

}