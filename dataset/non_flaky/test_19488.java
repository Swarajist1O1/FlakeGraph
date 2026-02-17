class DummyClass_19488 {
	@Test
	public void testFillIdToEObjectMap() {
		EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
		EClass root = createEClass(pack, "Root");
		EClass someType = createEClass(pack, "SomeType");

		EReference ref1 = addEReference(root, someType, "ref1", false);
		EReference ref2 = addEReference(root, someType, "ref2", true);

		EFactory factory = pack.getEFactoryInstance();
		EObject rootObject = factory.create(root);
		EObject someTypeObject1 = factory.create(someType);
		EObject someTypeObject2 = factory.create(someType);
		rootObject.eSet(ref1, someTypeObject1);
		rootObject.eSet(ref2, someTypeObject2);

		List<EObject> map = new ArrayList<>();
		SerializationUtil.fillIdToEObjectMap(rootObject, map);
		assertTrue(map.contains(rootObject));
		assertTrue(map.contains(someTypeObject1));
		assertFalse(map.contains(someTypeObject2));
		assertEquals(2, map.size());
	}

}