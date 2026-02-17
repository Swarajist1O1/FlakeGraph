class DummyClass_77661 {
    @Test public void missingMax() {
        @Property public void shouldHold(
            @InRange(min = "2012-12-31T23:59:59.999999999Z") Instant i) {

            assertThat(
                i,
                greaterThanOrEqualTo(Instant.parse("2012-12-31T23:59:59.999999999Z")));
        }

}