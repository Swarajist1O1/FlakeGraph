class DummyClass_76916 {
	@Test // #31
	public void returnsParticularRevisionForAnEntity() {

		Country de = new Country();
		de.code = "de";
		de.name = "Deutschland";

		countryRepository.save(de);

		de.name = "Germany";

		countryRepository.save(de);

		Revisions<Integer, Country> revisions = countryRepository.findRevisions(de.id);

		assertThat(revisions).hasSize(2);

		Iterator<Revision<Integer, Country>> iterator = revisions.iterator();
		Revision<Integer, Country> first = iterator.next();
		Revision<Integer, Country> second = iterator.next();

		assertThat(countryRepository.findRevision(de.id, first.getRequiredRevisionNumber())).hasValueSatisfying(it -> {
			assertThat(it.getEntity().name).isEqualTo("Deutschland");
		});

		assertThat(countryRepository.findRevision(de.id, second.getRequiredRevisionNumber())).hasValueSatisfying(it -> {
			assertThat(it.getEntity().name).isEqualTo("Germany");
		});
	}

}