class DummyClass_26745 {
	@Test
	public void testToString() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.toString(), is("developerId"));
	}

}