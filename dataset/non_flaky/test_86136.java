class DummyClass_86136 {
    @Test
    public void deserializeDateTime() throws Exception {
        final String json = "{\"date_time\":\"2016-12-13 14:00:00.000\"}";
        final DTO value = objectMapper.readValue(json, DTO.class);
        assertThat(value.dateTime).isEqualTo(new DateTime(2016, 12, 13, 14, 0, DateTimeZone.UTC));
    }

}