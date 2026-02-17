class DummyClass_76913 {
	@Test // #146
	public void findRevisionShortCircuitsOnEmptyRevisionList() {

		failOnEmptyRevisions();

		EnversRevisionRepositoryImplUnderTest<?, Object, ?> repository = new EnversRevisionRepositoryImplUnderTest<>(entityInformation, revisionEntityInformation, entityManager);

		repository.findRevisions(-999, PageRequest.of(0, 5));
	}

}