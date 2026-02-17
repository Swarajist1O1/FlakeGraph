class DummyClass_110175 {
	@Test
	public void testWriteUnboundedQuantityValue() throws RDFHandlerException,
			RDFParseException, IOException {
		QuantityValueConverter valueConverter = new QuantityValueConverter(
				this.rdfWriter, this.propertyRegister, this.rdfConversionBuffer);

		QuantityValue value = this.objectFactory.getQuantityValue(new BigDecimal(100));
		PropertyIdValue propertyIdValue = objectFactory.getPropertyIdValue(
				"P1081", "http://www.wikidata.org/entity/");
		Value valueURI = valueConverter.getRdfValue(value, propertyIdValue,
				false);
		valueConverter.writeValue(value, (Resource) valueURI);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(this.out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("UnboundedQuantityValue.rdf")));
	}

}