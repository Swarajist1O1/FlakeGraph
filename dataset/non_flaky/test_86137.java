class DummyClass_86137 {
    @Test
    public void deserializeIsoDateTime() throws Exception {
        final String json = "{\"date_time\":\"2016-12-13T14:00:00.000\"}";
        final DTO value = objectMapper.readValue(json, DTO.class);
        assertThat(value.dateTime).isEqualTo(new DateTime(2016, 12, 13, 14, 0, DateTimeZone.UTC));
    }

}