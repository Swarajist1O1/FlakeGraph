class DummyClass_110198 {
	@Test
	public void testWriteSimpleStatements() throws RDFHandlerException,
			RDFParseException, IOException {
		ItemDocument document = createTestItemDocument();
		this.rdfConverter.writeSimpleStatements(resource, document);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(
				RdfTestHelpers
						.parseRdf("\n<http://test.org/> <http://www.wikidata.org/prop/direct/P31> <http://www.wikidata.org/Q10> ;\n"
								+ "<http://www.wikidata.org/prop/direct/P279> <http://www.wikidata.org/Q11> .\n"),
				model);
	}

}