class DummyClass_112728 {
	@Test
	public void testCommitNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		proxy.commit(null);
		proxy.close();
	}

}