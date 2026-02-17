class DummyClass_112711 {
	@Test
	public void testBasicStuff() throws Exception {
		OurConnectionSource cs = new OurConnectionSource();
		assertFalse(cs.isSavedConnection(createMock(DatabaseConnection.class)));
		DatabaseConnection conn = cs.getReadOnlyConnection(null);
		assertNotNull(conn);
		assertNull(cs.getSpecialConnection(null));
		cs.saveSpecialConnection(conn);
		assertSame(conn, cs.getSpecialConnection(null));
		assertTrue(cs.isSavedConnection(conn));
		assertFalse(cs.isSavedConnection(createMock(DatabaseConnection.class)));
		DatabaseConnection conn2 = cs.getReadOnlyConnection(null);
		assertSame(conn, conn2);
		assertNotNull(conn2);
		cs.clearSpecialConnection(conn);
		assertNull(cs.getSpecialConnection(null));
		assertFalse(cs.isSavedConnection(conn));
		assertNull(cs.getSavedConnection());
		cs.close();
	}

}