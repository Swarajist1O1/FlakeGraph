class DummyClass_77708 {
    @Test public void shrinkingPrimitiveNegativeDouble() {
        @Property public void shouldHold(
            @InRange(minDouble = -4400.998877665544, maxDouble = -777.012301230123) double d) {

            values.add(d);

            fail();
        }

}