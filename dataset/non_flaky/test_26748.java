class DummyClass_26748 {
	@Test
	public void testGetPairingDaysDefault() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.getPairingDays(), is(0));
	}

}