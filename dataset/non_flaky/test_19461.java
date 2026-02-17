class DummyClass_19461 {
	@Test public void testLaziness() throws Exception {
				public Iterator<IEObjectDescription> iterator() {
					numberOfCalls++;
					return singleton(
							(IEObjectDescription) new EObjectDescription(QualifiedName.create(name),
									EcorePackage.Literals.EATTRIBUTE, null)).iterator();
				}

}