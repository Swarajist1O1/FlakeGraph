class DummyClass_104174 {
	@Test
	public void testTextWithCurlyBracesPrefix() {
		assertThat(
				this.helper.stripPrefix("{key:foo}{name:bar}textwith}brac{es{and}prefix"))
						.isEqualTo("textwith}brac{es{and}prefix");
	}

}