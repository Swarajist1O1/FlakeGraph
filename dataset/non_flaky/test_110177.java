class DummyClass_110177 {
	@Test
	public void testWriteGlobeCoordinatesValue() throws RDFHandlerException,
			RDFParseException, IOException {
		GlobeCoordinatesValueConverter valueConverter = new GlobeCoordinatesValueConverter(
				this.rdfWriter, this.propertyRegister, this.rdfConversionBuffer);

		GlobeCoordinatesValue value = this.objectFactory
				.getGlobeCoordinatesValue(51.033333333333, 13.733333333333,
						(GlobeCoordinatesValue.PREC_DECI_DEGREE),
						"http://www.wikidata.org/entity/Q2");
		PropertyIdValue propertyIdValue = objectFactory.getPropertyIdValue(
				"P625", "http://www.wikidata.org/entity/");
		Value valueURI = valueConverter.getRdfValue(value, propertyIdValue,
				false);
		valueConverter.writeValue(value, (Resource) valueURI);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("GlobeCoordinatesValue.rdf")));
	}

}