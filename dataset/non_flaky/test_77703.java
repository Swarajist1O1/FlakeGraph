class DummyClass_77703 {
    @Test public void wrapperDouble() {
        @Property public void shouldHold(Double d) {
            assertThat(d, greaterThanOrEqualTo(RangeAttributes.minDouble()));
            assertThat(d, lessThan(RangeAttributes.maxDouble()));
        }

}