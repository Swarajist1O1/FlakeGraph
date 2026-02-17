class DummyClass_77678 {
    @Test public void shrinkingBooleanFromFalse() {
        @Property public void shouldHold(boolean b) {
            values.add(b);

            assumeFalse(b);
            fail();
        }

}