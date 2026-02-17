class DummyClass_59576 {
	@Test
	public void matchedDatesTest3() {
		//æµè¯æåä¸å¤©
		List<Date> matchedDates = CronPatternUtil.matchedDates("0 0 */1 L * *", DateUtil.parse("2018-10-30 23:33:22"), 5, true);
		Assert.assertEquals(5, matchedDates.size());
		Assert.assertEquals("2018-10-31 00:00:00", matchedDates.get(0).toString());
		Assert.assertEquals("2018-10-31 01:00:00", matchedDates.get(1).toString());
		Assert.assertEquals("2018-10-31 02:00:00", matchedDates.get(2).toString());
		Assert.assertEquals("2018-10-31 03:00:00", matchedDates.get(3).toString());
		Assert.assertEquals("2018-10-31 04:00:00", matchedDates.get(4).toString());
	}

}