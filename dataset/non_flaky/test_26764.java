class DummyClass_26764 {
	@Test
	public void testSetDate() throws ParseException {
		DayPairs pairs = new DayPairs();
		Date expectedDate = new Date();
		pairs.setDate(expectedDate);
		
		assertThat(pairs.getDate(), is(equalTo(getDateWithoutTime(expectedDate))));
	}

}