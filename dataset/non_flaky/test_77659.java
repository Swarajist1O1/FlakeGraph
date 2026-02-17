class DummyClass_77659 {
    @Test public void malformedMax() {
        @Property public void shouldHold(
            @InRange(min = "06/01/2011T23:59:59.999999999Z", max = "*&@^#%$") Instant i) {
        }

}