class DummyClass_26740 {
	@Test
	public void testCompareTo() {
		Developer developer = new Developer("developerId");
		Developer developer2 = new Developer("developerId2");
		
		assertThat(developer.getId().compareTo(developer2.getId()), is(-1));
		assertThat(developer2.getId().compareTo(developer.getId()), is(1));
		assertThat(developer.getId().compareTo(developer.getId()), is(0));
	}

}