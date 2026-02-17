class DummyClass_19490 {
	@Test
	public void testClone_2() throws Exception {
		ResourceSetImpl sourceSet = new DerivedStateAwareResourceSet();
		DerivedStateAwareResource resource = (DerivedStateAwareResource) sourceSet.createResource(URI
				.createURI("http://derived.res"));
		boolean stateToCheck = !resource.isFullyInitialized();
		resource.setFullyInitialized(stateToCheck);
		
		Resource targetRes = EcoreUtil2.clone(new DerivedStateAwareResourceSet(), sourceSet).getResources().get(0);
		
		assertTrue(targetRes instanceof DerivedStateAwareResource);
		assertEquals("FullyInitialized flag not copied ", stateToCheck, ((DerivedStateAwareResource) targetRes).isFullyInitialized());
	}

}