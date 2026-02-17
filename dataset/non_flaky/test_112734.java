class DummyClass_112734 {
	@Test
	public void testCompileStatementStringStatementTypeFieldTypeArrayIntNull() throws Exception {
		DatabaseConnectionProxy proxy = new DatabaseConnectionProxy(null);
		assertNull(proxy.compileStatement("statment", StatementType.DELETE, null, 0, false));
		proxy.close();
	}

}