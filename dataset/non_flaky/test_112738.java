class DummyClass_112738 {
	@Test
	public void testUpdateNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertEquals(0, proxy.update("statment", null, null));
		proxy.close();
	}

}