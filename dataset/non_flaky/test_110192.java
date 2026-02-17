class DummyClass_110192 {
	@Test
	public void testStatementSimpleValue() throws RDFHandlerException,
			RDFParseException, IOException {
		Statement statement = objectFactory.createStatement("Q100", "P227");
		this.rdfConverter.writeStatement(statement);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("Statement.rdf")));
	}

}