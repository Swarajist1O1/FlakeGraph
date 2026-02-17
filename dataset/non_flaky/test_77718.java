class DummyClass_77718 {
    @Test public void shrinkingPrimitivePositiveFloat() {
        @Property public void shouldHold(@InRange(minFloat = 5.123123F, maxFloat = 111.2222F) float f) {
            values.add(f);

            fail();
        }

}