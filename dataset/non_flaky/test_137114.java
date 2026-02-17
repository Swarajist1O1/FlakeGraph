class DummyClass_137114 {
	@Test
	public void verifyEquals() throws Exception {
		MethodMetadata withMethod1 = getTagged(WithMethod.class);
		MethodMetadata withMethod2 = getTagged(WithMethod.class);
		MethodMetadata withMethodWithTwoArguments1 = getTagged(WithMethodWithTwoArguments.class);
		MethodMetadata withMethodWithTwoArguments2 = getTagged(WithMethodWithTwoArguments.class);

		assertThat(withMethod1.equals(null)).isFalse();

		assertThat(withMethod1.equals(withMethod1)).isTrue();
		assertThat(withMethod2.equals(withMethod2)).isTrue();
		assertThat(withMethod1.equals(withMethod2)).isTrue();
		assertThat(withMethod2.equals(withMethod1)).isTrue();

		assertThat(withMethodWithTwoArguments1.equals(withMethodWithTwoArguments1)).isTrue();
		assertThat(withMethodWithTwoArguments2.equals(withMethodWithTwoArguments2)).isTrue();
		assertThat(withMethodWithTwoArguments1.equals(withMethodWithTwoArguments2)).isTrue();
		assertThat(withMethodWithTwoArguments2.equals(withMethodWithTwoArguments1)).isTrue();

		assertThat(withMethod1.equals(withMethodWithTwoArguments1)).isFalse();
		assertThat(withMethodWithTwoArguments1.equals(withMethod1)).isFalse();
	}

}