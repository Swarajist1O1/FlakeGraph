class DummyClass_26774 {
	@Test
	public void testSimpleDateFormatNotPersisted() throws NoSuchFieldException, SecurityException {
		DayPairs pairs = new DayPairs();
		Field dateFormatterField = pairs.getClass().getDeclaredField("dateFormatter");
		dateFormatterField.setAccessible(true);
		Transient annotation = dateFormatterField.getAnnotation(Transient.class);
		
		assertThat(annotation, is(not(nullValue())));
	}

}