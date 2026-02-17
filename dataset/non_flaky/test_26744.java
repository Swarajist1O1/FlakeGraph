class DummyClass_26744 {
	@Test
	public void testEqualsOfDifferentInstances() {
		Developer developer = new Developer("developerId");
		Developer differentDeveloper = new Developer("developerId2");
		
		assertThat(developer.equals(differentDeveloper), is(false));
		assertThat(differentDeveloper.equals(developer), is(false));
	}

}