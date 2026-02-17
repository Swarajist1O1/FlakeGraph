class DummyClass_77660 {
    @Test public void missingMin() {
        public void shouldHold(
            @InRange(max = "2012-12-31T23:59:59.999999999Z") Instant i) {

            assertThat(
                i,
                lessThanOrEqualTo(Instant.parse("2012-12-31T23:59:59.999999999Z")));
        }

}