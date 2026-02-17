class DummyClass_77681 {
    @Test public void leftOpenEndedRangedPrimitiveByte() {
        @Property public void shouldHold(@InRange(max = "0") byte b) {
            assertThat(b, lessThanOrEqualTo((byte) 0));
        }

}