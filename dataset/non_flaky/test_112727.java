class DummyClass_112727 {
	@Test
	public void testCommit() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		conn.commit(null);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.commit(null);
		proxy.close();
		verify(conn);
	}

}