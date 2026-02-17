class DummyClass_26741 {
	@Test
	public void testHashCodeOfEqualInstances() {
		Developer developer = new Developer("developerId");
		Developer sameDeveloper = new Developer("developerId");
		
		assertThat(developer.hashCode(), is(sameDeveloper.hashCode()));
	}

}