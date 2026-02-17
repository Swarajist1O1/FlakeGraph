class DummyClass_77716 {
    @Test public void leftOpenEndedRangedWrapperFloat() {
        @Property public void shouldHold(@InRange(max = "3.14") Float f) {
            assertThat(f, greaterThanOrEqualTo(RangeAttributes.minFloat()));
            assertThat(f, lessThanOrEqualTo(3.14F));
        }

}