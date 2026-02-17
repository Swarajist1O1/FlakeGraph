class DummyClass_112744 {
	@Test
	public void testQueryForLongStringNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertEquals(0, proxy.queryForLong("statment"));
		proxy.close();
	}

}