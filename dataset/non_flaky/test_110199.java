class DummyClass_110199 {
	@Test
	public void testWriteInterPropertyLinks() throws RDFHandlerException,
			RDFParseException, IOException {
		PropertyDocument document = this.dataObjectFactory.getPropertyDocument(
				this.dataObjectFactory.getPropertyIdValue("P17",
						"http://www.wikidata.org/"), Collections
						.<MonolingualTextValue> emptyList(), Collections
						.<MonolingualTextValue> emptyList(), Collections
						.<MonolingualTextValue> emptyList(), Collections
						.<StatementGroup> emptyList(), this.dataObjectFactory
						.getDatatypeIdValue(DatatypeIdValue.DT_ITEM), 0);
		this.rdfConverter.writeInterPropertyLinks(document);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(out.toString());

		assertEquals(RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("InterPropertyLinks.rdf")), model);
	}

}