class DummyClass_112719 {
	@Test
	public void testIsAutoCommitSupported() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		boolean supported = true;
		expect(conn.isAutoCommitSupported()).andReturn(supported);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		assertEquals(supported, proxy.isAutoCommitSupported());
		proxy.close();
		verify(conn);
	}

}