class DummyClass_112714 {
	@Test
	public void testClearNone() {
		OurConnectionSource cs = new OurConnectionSource();
		cs.clearSpecialConnection(createMock(DatabaseConnection.class));
		cs.close();
	}

}