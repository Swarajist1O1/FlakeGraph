class DummyClass_26739 {
	@Test
	public void testHasContext() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.hasContext(), is(false));
		
		developer.setHasContext(true);
		
		assertThat(developer.hasContext(), is(true));
	}

}