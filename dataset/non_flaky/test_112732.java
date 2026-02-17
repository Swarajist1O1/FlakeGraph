class DummyClass_112732 {
	@Test
	public void testExecuteStatementNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertEquals(0, proxy.executeStatement("statment", 0));
		proxy.close();
	}

}