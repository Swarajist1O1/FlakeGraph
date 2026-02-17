class DummyClass_113917 {
	@Test
	public void findFromReplicasByIdOther() { // 5
		GetAnyReplicaOptions options = GetAnyReplicaOptions.getAnyReplicaOptions().timeout(Duration.ofSeconds(10));
		Airport saved = couchbaseTemplate.insertById(Airport.class).inScope(otherScope).inCollection(otherCollection)
				.one(vie);
		try {
			Airport found = couchbaseTemplate.findFromReplicasById(Airport.class).inScope(otherScope)
					.inCollection(otherCollection).withOptions(options).any(saved.getId());
			assertEquals(saved, found);
		} finally {
			couchbaseTemplate.removeById().inScope(otherScope).inCollection(otherCollection).one(saved.getId());
		}
	}

}