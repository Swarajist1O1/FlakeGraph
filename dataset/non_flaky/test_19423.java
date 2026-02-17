class DummyClass_19423 {
	@Test public void testCreateNull() {
	public void testAppendNull() {
		try {
			QualifiedName.create().append((String) null);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {}
	}

}