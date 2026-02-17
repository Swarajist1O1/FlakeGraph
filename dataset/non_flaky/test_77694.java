class DummyClass_77694 {
    @Test public void shrinkingPrimitiveCharacter() {
        @Property public void shouldHold(@InRange(minChar = ' ', maxChar = '\u00FF') char ch) {
            values.add(ch);

            fail();
        }

}