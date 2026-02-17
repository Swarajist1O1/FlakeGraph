class DummyClass_112721 {
	@Test
	public void testIsAutoCommit() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		boolean autoCommit = false;
		expect(conn.isAutoCommit()).andReturn(autoCommit);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(autoCommit, proxy.isAutoCommit());
		proxy.close();
		verify(conn);
	}

}