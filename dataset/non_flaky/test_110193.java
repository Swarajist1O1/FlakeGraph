class DummyClass_110193 {
	@Test
	public void testStatementComplexValue() throws RDFHandlerException,
			RDFParseException, IOException {
		GlobeCoordinatesValue value = Datamodel.makeGlobeCoordinatesValue(51,
				13, GlobeCoordinatesValue.PREC_DEGREE,
				GlobeCoordinatesValue.GLOBE_EARTH);
		Statement statement = StatementBuilder
				.forSubjectAndProperty(ItemIdValue.NULL, PropertyIdValue.NULL)
				.withValue(value).build();
		this.rdfConverter.writeStatement(statement);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("StatementCplx.rdf")));
	}

}