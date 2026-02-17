class DummyClass_26766 {
	@Test
	public void testCompareTo() {
		DayPairs todaysPairs = new DayPairs();
		todaysPairs.setDate(new Date());
		DayPairs yesterdayPairs = new DayPairs();
		yesterdayPairs.setDate(getYesterdayDate());
		
		assertThat(todaysPairs.compareTo(yesterdayPairs), is(equalTo(1)));
		assertThat(yesterdayPairs.compareTo(todaysPairs), is(equalTo(-1)));
		assertThat(todaysPairs.compareTo(todaysPairs), is(equalTo(0)));
	}

}