class DummyClass_77636 {
    @Test public void missingMax() {
       @Property public void shouldHold(@InRange(min = "P36Y2M3D") Period p) {
            assertThat(
                toBigInteger(p),
                greaterThanOrEqualTo(toBigInteger(Period.parse("P36Y2M3D"))));
        }

}