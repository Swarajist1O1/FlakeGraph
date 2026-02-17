class DummyClass_77707 {
    @Test public void shrinkingPrimitivePositiveDouble() {
        @Property public void shouldHold(
            @InRange(minDouble = 555.123123123123, maxDouble = 11111.222222222) double d) {

            values.add(d);

            fail();
        }

}