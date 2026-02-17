class DummyClass_77710 {
    @Test public void primitiveFloat() {
        @Property public void shouldHold(float f) {
            assertThat(f, greaterThanOrEqualTo(RangeAttributes.minFloat()));
            assertThat(f, lessThan(RangeAttributes.maxFloat()));
        }

}