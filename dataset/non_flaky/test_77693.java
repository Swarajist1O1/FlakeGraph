class DummyClass_77693 {
    @Test public void rightOpenEndedRangedPrimitiveCharacter() {
        @Property public void shouldHold(@InRange(minChar = '\uFF00') char ch) {
            assertThat(ch, greaterThanOrEqualTo('\uFF00'));
        }

}