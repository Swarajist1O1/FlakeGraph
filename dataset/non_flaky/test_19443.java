class DummyClass_19443 {
	@Test
	public void testReferences() throws Exception {
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		EClassInfo c = addClass("c");
		EClassInfo d = addClass("d");
		b.addSupertype(a);
		c.addSupertype(a);
		addReference(b, d, "r1");
		addReference(c, d, "r1");

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
		assertEquals(1, c.getEClass().getEStructuralFeatures().size());

		liftUpFeatures();

		assertEquals(1, a.getEClass().getEStructuralFeatures().size());
		assertEquals(0, b.getEClass().getEStructuralFeatures().size());
		assertEquals(0, c.getEClass().getEStructuralFeatures().size());
	}

}