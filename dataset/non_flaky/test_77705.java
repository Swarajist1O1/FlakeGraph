class DummyClass_77705 {
    @Test public void leftOpenEndedRangedWrapperDouble() {
        @Property public void shouldHold(@InRange(max = "3.14") Double d) {
            assertThat(d, greaterThanOrEqualTo(RangeAttributes.minDouble()));
            assertThat(d, lessThan(3.14));
        }

}