class DummyClass_77709 {
    @Test public void shrinkingPrimitiveDoubleStraddlingZero() {
        @Property public void shouldHold(double d) {
            values.add(d);

            fail();
        }

}