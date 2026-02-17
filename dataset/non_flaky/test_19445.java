class DummyClass_19445 {
	@Test
	public void testDublicateDerivedFeature() throws Exception {
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		EClassInfo c = addClass("c");
		b.addSupertype(a);
		c.addSupertype(b);
		addAttribute(a, INT, "f");
		addAttribute(c, INT, "f");

		assertEquals(1, a.getEClass().getEStructuralFeatures().size());
		assertEquals(0, b.getEClass().getEStructuralFeatures().size());
		assertEquals(1, c.getEClass().getEStructuralFeatures().size());

		initializeHelper();
		helper.removeDuplicateDerivedFeatures();

		assertEquals(1, a.getEClass().getEStructuralFeatures().size());
		assertEquals(0, b.getEClass().getEStructuralFeatures().size());
		assertEquals(0, c.getEClass().getEStructuralFeatures().size());
	}

}