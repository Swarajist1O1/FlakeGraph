class DummyClass_77626 {
    @Test public void missingMin() {
        @Property public void shouldHold(
            @InRange(max = "PT2562047788015215H30M7.999999999S") Duration d) {

            assertThat(
                d,
                lessThanOrEqualTo(
                    Duration.parse("PT2562047788015215H30M7.999999999S")));
        }

}