class DummyClass_26738 {
	@Test
	public void testNew() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.getNew(), is(false));
		
		developer.setNew(true);
		
		assertThat(developer.getNew(), is(true));
	}

}