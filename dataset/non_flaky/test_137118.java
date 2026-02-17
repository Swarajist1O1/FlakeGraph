class DummyClass_137118 {
	@Test
	public void getDeclaringClassReturnsDeclaringClass() {
		assertThat(getTagged(WithMethod.class).getDeclaringClassName()).isEqualTo(
				WithMethod.class.getName());
	}

}