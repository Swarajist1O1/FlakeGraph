class DummyClass_112743 {
	@Test
	public void testQueryForLongString() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "select stuff from foo";
		long result = 31231231241414L;
		expect(conn.queryForLong(statement)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.queryForLong(statement));
		proxy.close();
		verify(conn);
	}

}