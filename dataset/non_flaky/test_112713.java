class DummyClass_112713 {
	@Test(expected = SQLException.class)
	public void testSaveDifferentConnection() throws Exception {
		OurConnectionSource cs = new OurConnectionSource();
		DatabaseConnection conn = cs.getReadOnlyConnection(null);
		cs.saveSpecialConnection(conn);
		cs.saveSpecialConnection(createMock(DatabaseConnection.class));
		cs.close();
	}

}