class DummyClass_112715 {
	@Test
	public void testClearDifferentConnection() throws Exception {
		OurConnectionSource cs = new OurConnectionSource();
		DatabaseConnection conn = cs.getReadOnlyConnection(null);
		cs.saveSpecialConnection(conn);
		cs.clearSpecialConnection(createMock(DatabaseConnection.class));
		cs.close();
	}

}