class DummyClass_110189 {
	@Test
	public void testWriteItemDocumentWithNullPropertyTypes() throws RDFHandlerException,
			IOException, RDFParseException {
		this.rdfConverter = new RdfConverter(this.rdfWriter, this.sites,
				new MockPropertyRegister.WithNullPropertyTypes());

		ItemDocument document = this.objectFactory.createItemDocument();
		this.rdfConverter.writeItemDocument(document);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("ItemDocumentUnknownPropertyTypes.rdf")));
	}

}