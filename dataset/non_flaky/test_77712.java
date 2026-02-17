class DummyClass_77712 {
    @Test public void leftOpenEndedRangedPrimitiveFloat() {
        @Property public void shouldHold(@InRange(maxFloat = 3.14F) float f) {
            assertThat(f, greaterThanOrEqualTo(RangeAttributes.minFloat()));
            assertThat(f, lessThanOrEqualTo(3.14F));
        }

}