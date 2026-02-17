class DummyClass_112751 {
	@Test
	public void testIsClosed() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		boolean closed = true;
		expect(conn.isClosed()).andReturn(closed);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(closed, proxy.isClosed());
		proxy.close();
		verify(conn);
	}

}