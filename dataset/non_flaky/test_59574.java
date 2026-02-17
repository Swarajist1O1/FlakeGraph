class DummyClass_59574 {
	@Test
	public void matchedDatesTest() {
		//æµè¯æ¯30ç§æ§è¡
		List<Date> matchedDates = CronPatternUtil.matchedDates("0/30 * 8-18 * * ?", DateUtil.parse("2018-10-15 14:33:22"), 5, true);
		Assert.assertEquals(5, matchedDates.size());
		Assert.assertEquals("2018-10-15 14:33:30", matchedDates.get(0).toString());
		Assert.assertEquals("2018-10-15 14:34:00", matchedDates.get(1).toString());
		Assert.assertEquals("2018-10-15 14:34:30", matchedDates.get(2).toString());
		Assert.assertEquals("2018-10-15 14:35:00", matchedDates.get(3).toString());
		Assert.assertEquals("2018-10-15 14:35:30", matchedDates.get(4).toString());
	}

}