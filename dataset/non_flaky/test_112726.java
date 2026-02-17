class DummyClass_112726 {
	@Test
	public void testSetSavePointNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertNull(proxy.setSavePoint("name"));
		proxy.close();
	}

}