class DummyClass_77713 {
    @Test public void rightOpenEndedRangedPrimitiveFloat() {
        @Property public void shouldHold(@InRange(minFloat = -2.71F) float f) {
            assertThat(f, greaterThanOrEqualTo(-2.71F));
            assertThat(f, lessThan(RangeAttributes.maxFloat()));
        }

}