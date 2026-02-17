class DummyClass_19439 {
	@Test
	public void testSimpeCase02() throws Exception {
		// no uplift for less than two children
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		b.addSupertype(a);
		addAttribute(b, INT, "f1");

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());

		liftUpFeatures();

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
	}

}