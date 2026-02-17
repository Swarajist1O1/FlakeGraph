class DummyClass_26742 {
	@Test
	public void testHashCodeOfDifferentInstances() {
		Developer developer = new Developer("developerId");
		Developer differentDeveloper = new Developer("developerId2");
		
		assertThat(developer.hashCode(), is(not(differentDeveloper.hashCode())));
	}

}