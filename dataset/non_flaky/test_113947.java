class DummyClass_113947 {
	@Test
	public void testScopeCollectionAnnotationSwap() {
		// UserCol annotation scope is other_scope, collection is other_collection
		// airportRepository relies on Config.setScopeName(scopeName) ("my_scope") from CollectionAwareIntegrationTests.
		// using airportRepository without specified a collection should fail.
		// This test ensures that airportRepository.save(airport) doesn't get the
		// collection from CrudMethodMetadata of UserCol.save()
		UserCol userCol = new UserCol("1", "Dave", "Wilson");
		Airport airport = new Airport("3", "myIata", "myIcao");
		UserCol savedCol = userColRepository.save(userCol); // uses UserCol annotation scope, populates CrudMethodMetadata
		userColRepository.delete(userCol); // uses UserCol annotation scope, populates CrudMethodMetadata
		assertThrows(IllegalStateException.class, () -> airportRepository.save(airport));
	}

}