class DummyClass_112763 {
	@Test
	public void testCustomColumnNames() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		Foo foo = new Foo();
		foo.val = 1213213;
		assertEquals(1, dao.create(foo));
		final String idName = "SOME_ID";
		final String valName = "SOME_VAL";
		final AtomicBoolean gotResult = new AtomicBoolean(false);
		GenericRawResults<Object> results =
				dao.queryRaw("select id as " + idName + ", val as " + valName + " from foo",
						new RawRowMapper<Object>() {
							@Override
							public Object mapRow(String[] columnNames, String[] resultColumns) {
								assertEquals(idName, columnNames[0]);
								assertEquals(valName, columnNames[1]);
								gotResult.set(true);
								return new Object();
							}

}