class DummyClass_77634 {
    @Test public void malformedMax() {
        @Property public void shouldHold(
            @InRange(min = "P1Y2M3D", max = "*&@^#%$") Period p) {
        }

}