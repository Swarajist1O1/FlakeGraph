class DummyClass_110188 {
	@Test
	public void testWriteItemDocument() throws RDFHandlerException,
			IOException, RDFParseException {
		ItemDocument document = this.objectFactory.createItemDocument();
		this.rdfConverter.writeItemDocument(document);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("ItemDocument.rdf")));
	}

}