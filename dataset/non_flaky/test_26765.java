class DummyClass_26765 {
	@Test
	public void testGetDate() throws ParseException {
		assertThat(new DayPairs().getDate(), is(equalTo(getDateWithoutTime(new Date()))));
	}

}