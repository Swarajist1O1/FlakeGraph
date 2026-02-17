class DummyClass_112687 {
	@Test
	public void testExtractId() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		assertEquals(1, dao.create(one));
		assertEquals(one.id, (int) one.extractId());
	}

}