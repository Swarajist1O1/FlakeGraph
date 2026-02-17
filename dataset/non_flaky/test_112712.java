class DummyClass_112712 {
	@Test
	public void testNestedSave() throws Exception {
		OurConnectionSource cs = new OurConnectionSource();
		DatabaseConnection conn = cs.getReadOnlyConnection(null);
		cs.saveSpecialConnection(conn);
		cs.saveSpecialConnection(conn);
		cs.clearSpecialConnection(conn);
		assertEquals(conn, cs.getSpecialConnection(null));
		cs.close();
	}

}