class DummyClass_104181 {
	@Test
	public void testEscapedPlaceholdersRemoved() {
		assertThat(resolvePlaceholders(this.env, "\\${abc}")).isEqualTo("${abc}");
		// JSON generated from jackson will be double escaped
		assertThat(resolvePlaceholders(this.env, "\\\\${abc}")).isEqualTo("${abc}");
	}

}