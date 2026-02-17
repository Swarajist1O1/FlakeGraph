class DummyClass_112746 {
	@Test
	public void testQueryForLongStringObjectArrayFieldTypeArrayNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertEquals(0, proxy.queryForLong("statment", null, null));
		proxy.close();
	}

}