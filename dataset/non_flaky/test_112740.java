class DummyClass_112740 {
	@Test
	public void testDeleteNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertEquals(0, proxy.delete("statment", null, null));
		proxy.close();
	}

}