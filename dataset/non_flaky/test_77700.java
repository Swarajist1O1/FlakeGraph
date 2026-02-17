class DummyClass_77700 {
    @Test public void rangedPrimitiveDouble() {
        @Property public void shouldHold(@InRange(minDouble = -2.71, maxDouble = 3.14) double d) {
            assertThat(d, allOf(greaterThanOrEqualTo(-2.71), lessThan(3.14)));
        }

}