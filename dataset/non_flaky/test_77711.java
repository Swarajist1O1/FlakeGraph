class DummyClass_77711 {
    @Test public void rangedPrimitiveFloat() {
        @Property public void shouldHold(@InRange(minFloat = -2.51234F, maxFloat = 9.23423F) float f) {
            assertThat(f, allOf(greaterThanOrEqualTo(-2.51234F), lessThan(9.23423F)));
        }

}