class DummyClass_76914 {
	@Test
	public void testLifeCycle() {

		License license = new License();
		license.name = "Schnitzel";

		licenseRepository.save(license);

		Country de = new Country();
		de.code = "de";
		de.name = "Deutschland";

		countryRepository.save(de);

		Country se = new Country();
		se.code = "se";
		se.name = "Schweden";

		countryRepository.save(se);

		license.laender = new HashSet<Country>();
		license.laender.addAll(Arrays.asList(de, se));

		licenseRepository.save(license);

		de.name = "Daenemark";

		countryRepository.save(de);

		Optional<Revision<Integer, License>> revision = licenseRepository.findLastChangeRevision(license.id);

		assertThat(revision).hasValueSatisfying(it -> {

			Page<Revision<Integer, License>> page = licenseRepository.findRevisions(license.id, PageRequest.of(0, 10));
			Revisions<Integer, License> revisions = Revisions.of(page.getContent());
			assertThat(revisions.getLatestRevision()).isEqualTo(it);
		});
	}

}