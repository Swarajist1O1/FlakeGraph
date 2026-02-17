class DummyClass_77682 {
    @Test public void rightOpenEndedRangedPrimitiveByte() {
        @Property public void shouldHold(@InRange(min = "0") byte b) {
            assertThat(b, greaterThanOrEqualTo((byte) 0));
        }

}