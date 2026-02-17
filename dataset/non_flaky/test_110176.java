class DummyClass_110176 {
	@Test
	public void testWriteMonolingualTextValue() throws RDFHandlerException {
		MonolingualTextValueConverter valueConverter = new MonolingualTextValueConverter(
				this.rdfWriter, this.propertyRegister, this.rdfConversionBuffer);

		MonolingualTextValue value = this.objectFactory
				.getMonolingualTextValue("ä¸­åäººæ°å±åå½", "zh-hans");
		PropertyIdValue propertyIdValue = this.objectFactory
				.getPropertyIdValue("P1448", "http://www.wikidata.org/entity/");
		Value valueURI = valueConverter.getRdfValue(value, propertyIdValue,
				true);
		this.rdfWriter.finish();

		assertEquals(valueURI.toString(), "\"ä¸­åäººæ°å±åå½\"@zh-Hans");
	}

}