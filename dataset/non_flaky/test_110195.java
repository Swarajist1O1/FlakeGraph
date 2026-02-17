class DummyClass_110195 {
	@Test
	public void testWriteBasicDeclarations() throws RDFHandlerException,
			RDFParseException, IOException {
		this.rdfConverter.writeBasicDeclarations();
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("BasicDeclarations.rdf")), model);
	}

}