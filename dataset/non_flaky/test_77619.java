class DummyClass_77619 {
    @Test public void sizeConstrainedSets() {
        @Property public void shouldHold(@Size(min = 2, max = 5) Set<String> items) {
            assertThat(items.size(), lessThanOrEqualTo(5));
        }

}