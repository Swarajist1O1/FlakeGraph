class DummyClass_113907 {
	@Test
	public void findFromReplicasById() { // 5
		GetAnyReplicaOptions options = GetAnyReplicaOptions.getAnyReplicaOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(scopeName).inCollection(collectionName)
				.one(vie);
		try {
			Airport found = couchbaseTemplate.findFromReplicasById(Airport.class).inScope(scopeName)
					.inCollection(collectionName).withOptions(options).any(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId());
		}
	}

}