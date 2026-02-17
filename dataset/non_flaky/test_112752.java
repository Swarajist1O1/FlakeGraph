class DummyClass_112752 {
	@Test
	public void testIsClosedNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertTrue(proxy.isClosed());
		proxy.close();
	}

}