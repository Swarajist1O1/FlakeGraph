class DummyClass_77704 {
    @Test public void rangedWrapperDouble() {
        @Property public void shouldHold(@InRange(min = "2.71", max = "3.14") Double d) {
            assertThat(d, allOf(greaterThanOrEqualTo(2.71), lessThan(3.14)));
        }

}