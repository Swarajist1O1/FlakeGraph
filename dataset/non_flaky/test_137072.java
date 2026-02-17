class DummyClass_137072 {
	@Test
	public void metadataIsWrittenDeterministically() throws IOException {
		CandidateComponentsMetadata metadata = new CandidateComponentsMetadata();
		metadata.add(createItem("com.b", "type"));
		metadata.add(createItem("com.c", "type"));
		metadata.add(createItem("com.a", "type"));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PropertiesMarshaller.write(metadata, outputStream);
		String contents = new String(outputStream.toByteArray(), StandardCharsets.ISO_8859_1);
		assertThat(contents.split(System.lineSeparator())).containsExactly("com.a=type", "com.b=type", "com.c=type");
	}

}