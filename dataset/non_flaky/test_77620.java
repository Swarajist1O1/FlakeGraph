class DummyClass_77620 {
    @Test public void shrinkingSizeConstrainedSets() {
        @Property public void shouldHold(@Size(min = 2, max = 5) Set<Integer> items) {
            failed = items;

            fail();
        }

}