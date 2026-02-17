class DummyClass_112745 {
	@Test
	public void testQueryForLongStringObjectArrayFieldTypeArray() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "select stuff from foo";
		long result = 3123123124141413L;
		expect(conn.queryForLong(statement, null, null)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.queryForLong(statement, null, null));
		proxy.close();
		verify(conn);
	}

}