class DummyClass_77699 {
    @Test public void primitiveDouble() {
        @Property public void shouldHold(double d) {
            assertThat(d, greaterThanOrEqualTo(RangeAttributes.minDouble()));
            assertThat(d, lessThan(RangeAttributes.maxDouble()));
        }

}