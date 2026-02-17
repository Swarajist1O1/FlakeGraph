class DummyClass_98295 {
  @Test
  public void otherTimeZones() throws Exception {
    final Date expected =
        new DateTime(2016, 6, 3, 6, 57, 17, 478, DateTimeZone.forOffsetHours(-4)).toDate();
    assertThat(dockerDateFormat.parse("2016-06-03T06:57:17.4782869-04:00"), equalTo(expected));
  }

}