class DummyClass_112679 {
	@Test
	public void testCreate() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff = "fewpfjewfew";
		one.stuff = stuff;
		one.setDao(dao);
		assertEquals(1, one.create());
	}

}