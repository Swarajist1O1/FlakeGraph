class DummyClass_110178 {
	@Test
	public void testWriteTimeValue() throws RDFHandlerException,
			RDFParseException, IOException {
		TimeValueConverter valueConverter = new TimeValueConverter(
				this.rdfWriter, this.propertyRegister, this.rdfConversionBuffer);

		TimeValue value = objectFactory.getTimeValue(2008, (byte) 1, (byte) 1,
				(byte) 0, (byte) 0, (byte) 0, (byte) 9, 0, 0, 0,
				"http://www.wikidata.org/entity/Q1985727");
		PropertyIdValue propertyIdValue = objectFactory.getPropertyIdValue(
				"P569", "http://www.wikidata.org/entity/");
		Value valueURI = valueConverter.getRdfValue(value, propertyIdValue,
				false);
		valueConverter.writeValue(value, (Resource) valueURI);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("TimeValue.rdf")));
	}

}