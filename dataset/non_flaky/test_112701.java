class DummyClass_112701 {
	@Test(expected = IllegalStateException.class)
	public void testTransactionManagerNoSet() {
		TransactionManager tm = new TransactionManager();
		tm.initialize();
	}

}