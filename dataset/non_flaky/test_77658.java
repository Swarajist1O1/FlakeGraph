class DummyClass_77658 {
    @Test public void malformedMin() {
        @Property public void shouldHold(
            @InRange(min = "@#!@#@", max = "2012-12-31T23:59:59.999999999Z") Instant i) {
        }

}