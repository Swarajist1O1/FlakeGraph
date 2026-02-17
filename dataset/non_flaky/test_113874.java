class DummyClass_113874 {
	@Test
	public void findFromReplicasById() { // 5
		GetAnyReplicaOptions options = GetAnyReplicaOptions.getAnyReplicaOptions().timeout(Duration.ofSeconds(10));
		Airport saved = template.insertById(Airport.class).inScope(scopeName).inCollection(collectionName).one(vie.withIcao("lowb")).block();
		try {
			Airport found = template.findFromReplicasById(Airport.class).inScope(scopeName).inCollection(collectionName)
					.withOptions(options).any(saved.getId()).block();
			assertEquals(saved, found);
		} finally {
			template.removeById().inScope(scopeName).inCollection(collectionName).one(saved.getId()).block();
		}
	}

}