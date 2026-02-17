class DummyClass_110194 {
	@Test
	public void testStatementNoValue() throws RDFHandlerException,
			RDFParseException, IOException {
		PropertyIdValue pid = dataObjectFactory.getPropertyIdValue("P31", "http://www.wikidata.org/");
		Statement statement = StatementBuilder
				.forSubjectAndProperty(ItemIdValue.NULL, pid)
				.withNoValue().build();
		this.rdfConverter.writeStatement(statement);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("StatementNoValue.rdf")));
	}

}