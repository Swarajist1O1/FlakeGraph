class DummyClass_112737 {
	@Test
	public void testUpdate() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String statement = "insert bar";
		int result = 13212321;
		expect(conn.update(statement, null, null)).andReturn(result);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(result, proxy.update(statement, null, null));
		proxy.close();
		verify(conn);
	}

}