class DummyClass_59575 {
	@Test
	public void matchedDatesTest2() {
		//æµè¯æ¯å°æ¶æ§è¡
		List<Date> matchedDates = CronPatternUtil.matchedDates("0 0 */1 * * *", DateUtil.parse("2018-10-15 14:33:22"), 5, true);
		Assert.assertEquals(5, matchedDates.size());
		Assert.assertEquals("2018-10-15 15:00:00", matchedDates.get(0).toString());
		Assert.assertEquals("2018-10-15 16:00:00", matchedDates.get(1).toString());
		Assert.assertEquals("2018-10-15 17:00:00", matchedDates.get(2).toString());
		Assert.assertEquals("2018-10-15 18:00:00", matchedDates.get(3).toString());
		Assert.assertEquals("2018-10-15 19:00:00", matchedDates.get(4).toString());
	}

}