class DummyClass_112759 {
	@Test
	public void testQueryRaw() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		Foo foo = new Foo();
		foo.val = 1;
		foo.equal = 10;
		assertEquals(1, dao.create(foo));
		QueryBuilder<Foo, Integer> qb = dao.queryBuilder();
		qb.where().eq(Foo.VAL_COLUMN_NAME, new SelectArg());
		GenericRawResults<String[]> rawResults = dao.queryRaw(qb.prepareStatementString(), Integer.toString(foo.val));
		List<String[]> results = rawResults.getResults();
		assertEquals(1, results.size());
		boolean found = false;
		String[] columnNames = rawResults.getColumnNames();
		for (int i = 0; i < rawResults.getNumberColumns(); i++) {
			if (columnNames[i].equalsIgnoreCase(Foo.ID_COLUMN_NAME)) {
				assertEquals(Integer.toString(foo.id), results.get(0)[0]);
				found = true;
			}
		}
		assertTrue(found);
	}

}