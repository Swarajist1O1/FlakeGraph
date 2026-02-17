class DummyClass_110179 {
	@Test
	public void testSerialization() throws RDFParseException,
			RDFHandlerException, IOException {
		this.rdfSerializer.open();
		this.rdfSerializer.processItemDocument(this.objectFactory
				.createItemDocument());
		this.rdfSerializer.close();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("completeRDFDocument.rdf")), model);
	}

}