class DummyClass_112753 {
	@Test
	public void testIsTableExists() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		boolean tableExists = true;
		String tableName = "fjewfjwef";
		expect(conn.isTableExists(tableName)).andReturn(tableExists);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(tableExists, proxy.isTableExists(tableName));
		proxy.close();
		verify(conn);
	}

}