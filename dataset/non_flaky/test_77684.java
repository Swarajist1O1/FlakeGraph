class DummyClass_77684 {
    @Test public void shrinkingPrimitiveNegativeByte() {
        @Property public void shouldHold(@InRange(min = "-101", max = "-13") byte b) {
            values.add(b);

            fail();
        }

}