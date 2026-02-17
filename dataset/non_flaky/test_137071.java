class DummyClass_137071 {
	@Test
	public void readWrite() throws IOException {
		CandidateComponentsMetadata metadata = new CandidateComponentsMetadata();
		metadata.add(createItem("com.foo", "first", "second"));
		metadata.add(createItem("com.bar", "first"));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PropertiesMarshaller.write(metadata, outputStream);
		CandidateComponentsMetadata readMetadata = PropertiesMarshaller.read(
				new ByteArrayInputStream(outputStream.toByteArray()));
		assertThat(readMetadata).has(Metadata.of("com.foo", "first", "second"));
		assertThat(readMetadata).has(Metadata.of("com.bar", "first"));
		assertThat(readMetadata.getItems()).hasSize(2);
	}

}