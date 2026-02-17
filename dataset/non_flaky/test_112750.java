class DummyClass_112750 {
	@Test
	public void testCloseQuietlyNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		proxy.closeQuietly();
		proxy.close();
	}

}