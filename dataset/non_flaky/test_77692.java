class DummyClass_77692 {
    @Test public void leftOpenEndedRangedPrimitiveCharacter() {
        @Property public void shouldHold(@InRange(maxChar = '\u00FF') char ch) {
            assertThat(ch, lessThanOrEqualTo('\u00FF'));
        }

}