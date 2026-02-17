class DummyClass_112720 {
	@Test
	public void testIsAutoCommitSupportedNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertFalse(proxy.isAutoCommitSupported());
		proxy.close();
	}

}