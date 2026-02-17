class DummyClass_77701 {
    @Test public void leftOpenEndedRangedPrimitiveDouble() {
        @Property public void shouldHold(@InRange(maxDouble = 3.14) double d) {
            assertThat(d, greaterThanOrEqualTo(RangeAttributes.minDouble()));
            assertThat(d, lessThan(3.14));
        }

}