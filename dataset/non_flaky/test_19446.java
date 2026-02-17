class DummyClass_19446 {
	@Test public void testContainsCompatibleFeature_01() throws Exception {
	public void testChangeable(){
		EcorePackage pack = EcorePackage.eINSTANCE;
		EClass eClass = pack.getEClass();
		EClassInfo objectUnderTest = new EClassifierInfo.EClassInfo(eClass, false, Collections.<String>emptySet(), null);
		EcoreFactory fac = EcoreFactory.eINSTANCE;
		EReference reference = fac.createEReference();
		reference.setName("newReference");
		reference.setEType(eClass);
		reference.setChangeable(true);
		reference.setContainment(true);
		eClass.getEStructuralFeatures().add(reference);
		assertEquals(true,objectUnderTest.containsCompatibleFeature("newReference", false, true, eClass, new StringBuilder()));
		reference.setChangeable(false);
		assertEquals(false,objectUnderTest.containsCompatibleFeature("newReference", false, true, eClass, new StringBuilder()));
	}

}