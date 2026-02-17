class DummyClass_77633 {
    @Test public void malformedMin() {
        @Property public void shouldHold(
            @InRange(min = "@#!@#@", max = "P36Y2M3D") Period p) {
        }

}