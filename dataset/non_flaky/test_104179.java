class DummyClass_104179 {
	@Test
	public void testDifferentKeyDefaultSecret() {
		this.locator.setSecretLocator(new SecretLocator() {

			@Override
			public char[] locate(String secret) {
				assertThat(secret).isEqualTo("changeme");
				// The actual secret for "mykey" is the same as the keystore password
				return "letmein".toCharArray();
			}

}