class DummyClass_112700 {
	@Test
	public void testTransactionManagerSpringWiring() {
		TransactionManager tm = new TransactionManager();
		tm.setConnectionSource(connectionSource);
		tm.initialize();
	}

}