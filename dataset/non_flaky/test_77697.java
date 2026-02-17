class DummyClass_77697 {
    @Test public void leftOpenEndedRangedWrapperCharacter() {
        @Property public void shouldHold(@InRange(max = "\u00FF") Character ch) {
            assertThat(ch, lessThanOrEqualTo('\u00FF'));
        }

}