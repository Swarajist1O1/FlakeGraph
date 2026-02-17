class DummyClass_26749 {
	@Test
	public void testGetPairingDaysOne() {
		Developer developer = new Developer("developerId");
		
		developer.udpatePairingDays();
		
		assertThat(developer.getPairingDays(), is(1));
	}

}