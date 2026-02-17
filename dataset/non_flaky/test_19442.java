class DummyClass_19442 {
	@Test
	public void testImcompatipleFeatures() throws Exception {
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		EClassInfo c = addClass("c");
		b.addSupertype(a);
		c.addSupertype(a);
		addAttribute(b, INT, "f1");
		addAttribute(c, STRING, "f1");

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
		assertEquals(1, c.getEClass().getEStructuralFeatures().size());

		liftUpFeatures();

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
		assertEquals(1, c.getEClass().getEStructuralFeatures().size());
	}

}