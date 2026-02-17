class DummyClass_112754 {
	@Test
	public void testIsTableExistsNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertFalse(proxy.isTableExists("foo"));
		proxy.close();
	}

}