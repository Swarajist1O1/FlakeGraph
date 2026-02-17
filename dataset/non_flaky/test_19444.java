class DummyClass_19444 {
	@Test
	public void testConfigurationOfLiftedReference() throws Exception {
		EClassInfo a = addClass("a");
		EClassInfo b = addClass("b");
		EClassInfo c = addClass("c");

		b.addSupertype(a);
		c.addSupertype(a);
		EReference refB = addReference(b, a, "ref");
		refB.setContainment(true);
		EReference refC = addReference(c, a, "ref");
		refC.setContainment(true);

		assertEquals(0, a.getEClass().getEStructuralFeatures().size());
		assertEquals(1, b.getEClass().getEStructuralFeatures().size());
		assertEquals(1, c.getEClass().getEStructuralFeatures().size());

		liftUpFeatures();

		assertEquals(1, a.getEClass().getEStructuralFeatures().size());
		assertEquals(0, b.getEClass().getEStructuralFeatures().size());
		assertEquals(0, c.getEClass().getEStructuralFeatures().size());

		EReference refA = (EReference) a.getEClass().getEStructuralFeatures().get(0);
		assertTrue(refA.isContainment());
	}

}