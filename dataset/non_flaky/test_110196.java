class DummyClass_110196 {
	@Test
	public void testWriteNamespaceDeclarations() throws RDFHandlerException,
			RDFParseException, IOException {
		this.rdfConverter.writeNamespaceDeclarations();
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("Namespaces.rdf")), model);
	}

}