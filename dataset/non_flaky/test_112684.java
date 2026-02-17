class DummyClass_112684 {
	@Test
	public void testToString() throws Exception {
		Dao<One, Integer> dao = createDao(One.class, true);
		One one = new One();
		String stuff1 = "fewpfjewfew";
		one.stuff = stuff1;
		assertEquals(1, dao.create(one));
		String str = one.objectToString();
		assertTrue(str.contains("id=" + one.id));
		assertTrue(str.contains("stuff=" + stuff1));
	}

}