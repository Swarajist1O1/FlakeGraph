class DummyClass_77715 {
    @Test public void rangedWrapperFloat() {
        @Property public void shouldHold(@InRange(min = "-0.1234", max = "0.000123") Float f) {
            assertThat(f, allOf(greaterThanOrEqualTo(-0.1234F), lessThan(0.000123F)));
        }

}