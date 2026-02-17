class DummyClass_112742 {
	@Test
	public void testQueryForOneNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertNull(proxy.queryForOne("statment", null, null, null, null));
		proxy.close();
	}

}