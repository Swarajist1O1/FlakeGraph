class DummyClass_112749 {
	@Test
	public void testCloseQuietly() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		conn.closeQuietly();
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.closeQuietly();
		proxy.close();
		verify(conn);
	}

}