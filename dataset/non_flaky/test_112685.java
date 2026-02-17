class DummyClass_112685 {
	@Test
	public void testObjectEquals() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		assertEquals(1, dao.create(one));
		assertTrue(one.objectsEqual(one));
	}

}