class DummyClass_112739 {
	@Test
	public void testDelete() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "insert bar";
		int result = 13872321;
		expect(conn.delete(statement, null, null)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.delete(statement, null, null));
		proxy.close();
		verify(conn);
	}

}