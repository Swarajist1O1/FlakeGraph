class DummyClass_112736 {
	@Test
	public void testInsertNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertEquals(0, proxy.insert("statment", null, null, null));
		proxy.close();
	}

}