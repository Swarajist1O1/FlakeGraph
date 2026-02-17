class DummyClass_112729 {
	@Test
	public void testRollback() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		conn.rollback(null);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.rollback(null);
		proxy.close();
		verify(conn);
	}

}