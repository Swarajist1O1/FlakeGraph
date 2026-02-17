class DummyClass_77717 {
    @Test public void rightOpenEndedRangedWrapperFloat() {
        @Property public void shouldHold(@InRange(min = "-2.71") Float f) {
            assertThat(f, greaterThanOrEqualTo(-2.71F));
            assertThat(f, lessThan(RangeAttributes.maxFloat()));
        }

}