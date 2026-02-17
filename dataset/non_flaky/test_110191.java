class DummyClass_110191 {
	@Test
	public void testWriteStatementRankTriple() throws RDFHandlerException,
			RDFParseException, IOException {
		StatementRank rank = StatementRank.DEPRECATED;
		Resource subject = this.rdfFactory
				.createIRI("http://www.wikidata.org/Q10Snone");
		this.rdfConverter.writeStatementRankTriple(subject, rank);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("StatementRankTriple.rdf")), model);
	}

}