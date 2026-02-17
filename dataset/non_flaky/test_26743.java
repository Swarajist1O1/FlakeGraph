class DummyClass_26743 {
	@Test
	public void testEqualsOfEqualInstances() {
		Developer developer = new Developer("developerId");
		Developer sameDeveloper = new Developer("developerId");
		
		assertThat(developer.equals(sameDeveloper), is(true));
		assertThat(sameDeveloper.equals(developer), is(true));
	}

}