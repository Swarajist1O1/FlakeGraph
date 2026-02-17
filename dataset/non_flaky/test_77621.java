class DummyClass_77621 {
    @Test public void outOfWhackSizeRange() {
        @Property public void shouldHold(@Size(min = 4, max = 3) Set<String> items) {
            assertThat(items.size(), lessThanOrEqualTo(3));
        }

}