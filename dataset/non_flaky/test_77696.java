class DummyClass_77696 {
    @Test public void rangedWrapperCharacter() {
        @Property public void shouldHold(@InRange(min = "0", max = "9") Character ch) {
            assertThat(ch, allOf(greaterThanOrEqualTo('0'), lessThanOrEqualTo('9')));
        }

}