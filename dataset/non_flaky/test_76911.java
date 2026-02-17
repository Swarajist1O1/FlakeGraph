class DummyClass_76911 {
	@Test
	public void testWithQueryDsl() {

		Country de = new Country();
		de.code = "de";
		de.name = "Deutschland";

		countryRepository.save(de);

		Country found = countryRepository.findOne(QCountry.country.name.eq("Deutschland")).get();

		assertThat(found).isNotNull();
		assertThat(found.id).isEqualTo(de.id);
	}

}