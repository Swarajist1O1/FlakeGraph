class DummyClass_104173 {
	@Test
	public void testTextWithCurlyBracesNoPrefix() {
		assertThat(this.helper.stripPrefix("textwith}brac{es"))
				.isEqualTo("textwith}brac{es");
	}

}