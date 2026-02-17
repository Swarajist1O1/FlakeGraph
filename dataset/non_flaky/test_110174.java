class DummyClass_110174 {
	@Test
	public void testWriteQuantityValue() throws RDFHandlerException,
			RDFParseException, IOException {
		QuantityValueConverter valueConverter = new QuantityValueConverter(
				this.rdfWriter, this.propertyRegister, this.rdfConversionBuffer);

		QuantityValue value = this.objectFactory.getQuantityValue(
				new BigDecimal(100), new BigDecimal(100), new BigDecimal(100));
		PropertyIdValue propertyIdValue = objectFactory.getPropertyIdValue(
				"P1081", "http://www.wikidata.org/entity/");
		Value valueURI = valueConverter.getRdfValue(value, propertyIdValue,
				false);
		valueConverter.writeValue(value, (Resource) valueURI);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("QuantityValue.rdf")));
	}

}