class DummyClass_77698 {
    @Test public void rightOpenEndedRangedWrapperCharacter() {
        @Property public void shouldHold(@InRange(min = "\uFF00") Character ch) {
            assertThat(ch, greaterThanOrEqualTo('\uFF00'));
        }

}