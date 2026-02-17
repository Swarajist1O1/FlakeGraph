class DummyClass_77702 {
    @Test public void rightOpenEndedRangedPrimitiveDouble() {
        @Property public void shouldHold(@InRange(minDouble = -2.71) double d) {
            assertThat(d, greaterThanOrEqualTo(-2.71));
            assertThat(d, lessThan(RangeAttributes.maxDouble()));
        }

}