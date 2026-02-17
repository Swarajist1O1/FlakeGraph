class DummyClass_112762 {
	@Test
	public void testGetFirstResult() throws Exception {
		Dao<Foo, Integer> dao = createDao(Foo.class, true);
		Foo foo1 = new Foo();
		foo1.val = 342;
		assertEquals(1, dao.create(foo1));
		Foo foo2 = new Foo();
		foo2.val = 9045342;
		assertEquals(1, dao.create(foo2));

		QueryBuilder<Foo, Integer> qb = dao.queryBuilder();
		qb.selectRaw("MAX(" + Foo.VAL_COLUMN_NAME + ")");
		GenericRawResults<String[]> results = dao.queryRaw(qb.prepareStatementString());
		String[] result = results.getFirstResult();
		int max = Integer.parseInt(result[0]);
		if (foo1.val > foo2.val) {
			assertEquals(foo1.val, max);
		} else {
			assertEquals(foo2.val, max);
		}
	}

}