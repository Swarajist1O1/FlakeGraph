class DummyClass_112760 {
	@Test
	public void testQueryRawColumns() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		Foo foo1 = new Foo();
		foo1.val = 1;
		foo1.equal = 10;
		assertEquals(1, dao.create(foo1));
		Foo foo2 = new Foo();
		foo2.val = 10;
		foo2.equal = 5;
		assertEquals(1, dao.create(foo2));
		QueryBuilder<Foo, Integer> qb = dao.queryBuilder();
		qb.selectRaw("COUNT(*)");
		GenericRawResults<String[]> rawResults = dao.queryRaw(qb.prepareStatementString());
		List<String[]> results = rawResults.getResults();
		assertEquals(1, results.size());
		// 2 rows inserted
		assertEquals("2", results.get(0)[0]);

		qb = dao.queryBuilder();
		qb.selectRaw("MIN(val)", "MAX(val)");
		rawResults = dao.queryRaw(qb.prepareStatementString());
		results = rawResults.getResults();
		assertEquals(1, results.size());
		String[] result = results.get(0);
		assertEquals(2, result.length);
		// foo1 has the maximum value
		assertEquals(Integer.toString(foo1.val), result[0]);
		// foo2 has the maximum value
		assertEquals(Integer.toString(foo2.val), result[1]);
	}

}