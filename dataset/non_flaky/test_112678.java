class DummyClass_112678 {
	@Test
	public void testSerializableClass() throws SQLException {
		@SuppressWarnings("unused")
		Dao<SerializableWrapper, Integer> dao = createDao(SerializableWrapper.class, true);
		SerializableStuff stuff = new SerializableStuff();
		stuff.field1 = 12345;
		stuff.field2 = "oejwepfjw";
		SerializableWrapper wrapper = new SerializableWrapper();
		wrapper.stuff = stuff;

		assertEquals(1, dao.create(wrapper));

		SerializableWrapper result = dao.queryForId(wrapper.id);
		assertNotNull(result);
		assertEquals(wrapper.id, result.id);
		assertEquals(wrapper.stuff, result.stuff);
	}

}