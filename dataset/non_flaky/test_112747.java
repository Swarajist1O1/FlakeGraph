class DummyClass_112747 {
	@Test
	public void testClose() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.close();
		verify(conn);
	}

}