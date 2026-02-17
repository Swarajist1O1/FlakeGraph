class DummyClass_112723 {
	@Test
	public void testSetAutoCommit() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		boolean autoCommit = false;
		conn.setAutoCommit(autoCommit);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.setAutoCommit(autoCommit);
		proxy.close();
		verify(conn);
	}

}