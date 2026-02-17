class DummyClass_113900 {
	@Test
	public void testNullValue() {
		QueryCriteria c = where(i("name")).is(null);
		assertEquals("`name` = null", c.export());
	}

}