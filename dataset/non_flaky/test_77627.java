class DummyClass_77627 {
    @Test public void missingMax() {
        @Property public void shouldHold(
            @InRange(min = "PT-2562047788015215H-30M-8S") Duration d) {

            assertThat(
                d,
                greaterThanOrEqualTo(
                    Duration.parse("PT-2562047788015215H-30M-8S")));
        }

}