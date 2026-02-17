class DummyClass_77689 {
    @Test public void rightOpenEndedRangedWrapperByte() {
        @Property public void shouldHold(@InRange(min = "0") Byte b) {
            assertThat(b, greaterThanOrEqualTo((byte) 0));
        }

}