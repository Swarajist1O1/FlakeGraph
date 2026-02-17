class DummyClass_77632 {
    @Test public void rangedPeriod() {
        @Property public void shouldHold(
            @InRange(min = "P1Y2M3D", max = "P36Y2M3D") Period p) {

            assertThat(
                toBigInteger(p),
                allOf(
                    greaterThanOrEqualTo(toBigInteger(Period.parse("P1Y2M3D"))),
                    lessThanOrEqualTo(toBigInteger(Period.parse("P36Y2M3D")))));
        }

}