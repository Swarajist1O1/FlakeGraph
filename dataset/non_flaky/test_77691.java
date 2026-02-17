class DummyClass_77691 {
    @Test public void rangedPrimitiveCharacter() {
        @Property public void shouldHold(@InRange(minChar = 'a', maxChar = 'z') char ch) {
            assertThat(ch, allOf(greaterThanOrEqualTo('a'), lessThanOrEqualTo('z')));
        }

}