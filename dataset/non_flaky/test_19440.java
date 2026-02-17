class DummyClass_19440 {
	@Test
	public void testRecursiveUplift01() throws Exception {
		// no uplift for less than two children
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		EClassInfo c = addClass("c");
		EClassInfo d = addClass("d");
		EClassInfo e = addClass("e");
		b.addSupertype(a);
		c.addSupertype(a);
		d.addSupertype(c);
		e.addSupertype(c);

		addAttribute(b, INT, "f1");
		addAttribute(d, INT, "f1");
		addAttribute(e, INT, "f1");

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
		assertEquals(0, c.getEClass().getEStructuralFeatures().size());
		assertEquals(1, d.getEClass().getEStructuralFeatures().size());
		assertEquals(1, e.getEClass().getEStructuralFeatures().size());

		liftUpFeatures();

		assertEquals(1, a.getEClass().getEStructuralFeatures().size());
		assertEquals(0, b.getEClass().getEStructuralFeatures().size());
		assertEquals(0, c.getEClass().getEStructuralFeatures().size());
		assertEquals(0, d.getEClass().getEStructuralFeatures().size());
		assertEquals(0, e.getEClass().getEStructuralFeatures().size());
	}

}