class DummyClass_19438 {
	@Test
	public void testSimpeCase01() throws Exception {
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		EClassInfo c = addClass("c");
		b.addSupertype(a);
		c.addSupertype(a);
		addAttribute(b, INT, "f1");
		addAttribute(c, INT, "f1");

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
		assertEquals(1, c.getEClass().getEStructuralFeatures().size());

		liftUpFeatures();

		assertEquals(1, a.getEClass().getEStructuralFeatures().size());
		assertEquals(0, b.getEClass().getEStructuralFeatures().size());
		assertEquals(0, c.getEClass().getEStructuralFeatures().size());
	}

}