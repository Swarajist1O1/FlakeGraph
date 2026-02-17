class DummyClass_104162 {
		@Test
		public void symmetricEncryptionEnabled() throws Exception {
			ResponseEntity<String> entity = this.testRestTemplate
					.getForEntity("/encrypt/status", String.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		}

}