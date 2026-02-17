class DummyClass_77680 {
    @Test public void rangedPrimitiveByte() {
        @Property public void shouldHold(@InRange(min = "-23", max = "34") byte b) {
            assertThat(b, allOf(greaterThanOrEqualTo((byte) -23), lessThanOrEqualTo((byte) 34)));
        }

}