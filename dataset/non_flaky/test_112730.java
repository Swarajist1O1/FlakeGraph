class DummyClass_112730 {
	@Test
	public void testRollbackNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		proxy.rollback(null);
		proxy.close();
	}

}