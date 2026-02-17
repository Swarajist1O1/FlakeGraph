class DummyClass_26785 {
	@Test
	public void testIsDevOpsRotationWeekly() {
		Company company = new Company("Company");

		company.setDevOpsRotationStrategy("weekly");

		assertThat(company.isDevOpsRotationWeekly(), is(true));
	}

}