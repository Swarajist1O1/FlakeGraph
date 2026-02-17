class DummyClass_77683 {
    @Test public void shrinkingPrimitivePositiveByte() {
        @Property public void shouldHold(@InRange(min = "3", max = "110") byte b) {
            values.add(b);

            fail();
        }

}