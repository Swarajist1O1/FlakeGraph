class DummyClass_77688 {
    @Test public void leftOpenEndedRangedWrapperByte() {
        @Property public void shouldHold(@InRange(max = "0") Byte b) {
            assertThat(b, lessThanOrEqualTo((byte) 0));
        }

}