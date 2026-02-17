class DummyClass_76919 {
	@Test // #146
	public void shortCurcuitingWhenOffsetIsToLarge() {
		Country de = new Country();
		de.code = "de";
		de.name = "Deutschland";

		countryRepository.save(de);

		countryRepository.delete(de);

		check(de, 0, 1);
		check(de, 1, 1);
		check(de, 2, 0);
	}

}