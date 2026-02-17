class DummyClass_77706 {
    @Test public void rightOpenEndedRangedWrapperDouble() {
        @Property public void shouldHold(@InRange(min = "-2.71") Double d) {
            assertThat(d, greaterThanOrEqualTo(-2.71));
            assertThat(d, lessThan(RangeAttributes.maxDouble()));
        }

}