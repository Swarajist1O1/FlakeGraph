class DummyClass_112722 {
	@Test
	public void testIsAutoCommitNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertFalse(proxy.isAutoCommit());
		proxy.close();
	}

}