class DummyClass_104163 {
		@Test
		public void symmetricEncryptionBootstrapConfig() throws Exception {
			ResponseEntity<String> entity = this.testRestTemplate
					.getForEntity("/encrypt/status", String.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		}

}