class DummyClass_77714 {
    @Test public void wrapperFloat() {
        @Property public void shouldHold(Float f) {
            assertThat(f, greaterThanOrEqualTo(RangeAttributes.minFloat()));
            assertThat(f, lessThan(RangeAttributes.maxFloat()));
        }

}