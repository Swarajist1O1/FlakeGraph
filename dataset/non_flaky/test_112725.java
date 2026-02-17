class DummyClass_112725 {
	@Test
	public void testSetSavePoint() throws Exception {
		DatabaseConnection conn = createMock(DatabaseConnection.class);
		String name = "savepoint";
		expect(conn.setSavePoint(name)).andReturn(null);
		conn.close();
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(conn);
		replay(conn);
		proxy.setSavePoint(name);
		proxy.close();
		verify(conn);
	}

}