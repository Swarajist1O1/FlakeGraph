class DummyClass_112761 {
	@Test
	public void testHaving() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);

		Foo foo = new Foo();
		int val1 = 243342;
		foo.val = val1;
		assertEquals(1, dao.create(foo));
		foo = new Foo();
		foo.val = val1;
		assertEquals(1, dao.create(foo));
		foo = new Foo();
		// only one of these
		int val2 = 6543;
		foo.val = val2;
		assertEquals(1, dao.create(foo));

		QueryBuilder<Foo, Integer> qb = dao.queryBuilder();
		qb.selectColumns(Foo.VAL_COLUMN_NAME);
		qb.groupBy(Foo.VAL_COLUMN_NAME);
		qb.having("COUNT(VAL) > 1");
		GenericRawResults<String[]> results = dao.queryRaw(qb.prepareStatementString());
		List<String[]> list = results.getResults();
		// only val2 has 2 of them
		assertEquals(1, list.size());
		assertEquals(String.valueOf(val1), list.get(0)[0]);

		qb.having("COUNT(VAL) > 2");
		results = dao.queryRaw(qb.prepareStatementString());
		list = results.getResults();
		assertEquals(0, list.size());
	}

}