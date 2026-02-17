class DummyClass_26735 {
	@Test
	public void testId() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.getId(), is("developerId"));
	}

}