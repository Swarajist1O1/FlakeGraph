class DummyClass_77687 {
    @Test public void rangedWrapperByte() {
        @Property public void shouldHold(@InRange(min = "-3", max = "2") Byte b) {
            assertThat(b, allOf(greaterThanOrEqualTo((byte) -3), lessThanOrEqualTo((byte) 2)));
        }

}