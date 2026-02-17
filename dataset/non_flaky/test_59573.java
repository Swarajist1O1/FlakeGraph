class DummyClass_59573 {
	@Test(expected = CronException.class)
	public void rangeYearTest() {
		// yearçèå´æ¯1970~2099å¹´ï¼è¶åºæ¥é
		CronPattern pattern = new CronPattern("0/1 * * * 1/1 ? 2020-2120");
	}

}