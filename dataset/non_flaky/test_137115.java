class DummyClass_137115 {
	@Test
	public void verifyHashCode() throws Exception {
		MethodMetadata withMethod1 = getTagged(WithMethod.class);
		MethodMetadata withMethod2 = getTagged(WithMethod.class);
		MethodMetadata withMethodWithTwoArguments1 = getTagged(WithMethodWithTwoArguments.class);
		MethodMetadata withMethodWithTwoArguments2 = getTagged(WithMethodWithTwoArguments.class);

		assertThat(withMethod1).hasSameHashCodeAs(withMethod2);
		assertThat(withMethodWithTwoArguments1).hasSameHashCodeAs(withMethodWithTwoArguments2);

		assertThat(withMethod1).doesNotHaveSameHashCodeAs(withMethodWithTwoArguments1);
	}

}