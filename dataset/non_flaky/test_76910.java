class DummyClass_76910 {
	@Test // #112
	public void createsLocalDateTimeFromTimestamp() {

		DefaultRevisionEntity entity = new DefaultRevisionEntity();
		entity.setTimestamp(NOW.toEpochMilli());

		DefaultRevisionMetadata metadata = new DefaultRevisionMetadata(entity);

		assertThat(metadata.getRevisionDate()).hasValue(LocalDateTime.ofInstant(NOW, ZoneOffset.systemDefault()));
	}

}