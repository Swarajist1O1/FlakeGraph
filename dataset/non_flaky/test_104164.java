class DummyClass_104164 {
		@Test
		public void keystoreBootstrapConfig() throws Exception {
			ResponseEntity<String> entity = this.testRestTemplate
					.getForEntity("/encrypt/status", String.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		}

}