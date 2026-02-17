class DummyClass_77685 {
    @Test public void shrinkingPrimitiveByteStraddlingZero() {
        @Property public void shouldHold(byte b) {
            values.add(b);

            fail();
        }

}