class DummyClass_77635 {
    @Test public void missingMin() {
        @Property public void shouldHold(@InRange(max = "P36Y2M3D") Period p) {
            assertThat(
                toBigInteger(p),
                lessThanOrEqualTo(toBigInteger(Period.parse("P36Y2M3D"))));
        }

}