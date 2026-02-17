class DummyClass_112724 {
	@Test
	public void testSetAutoCommitNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		proxy.setAutoCommit(false);
		proxy.close();
	}

}