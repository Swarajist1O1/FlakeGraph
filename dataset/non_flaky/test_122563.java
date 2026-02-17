class DummyClass_122563 {
    @Test
    public void testMapEachLine() {
        assertEquals(
                1 + 2 + 3,
                terminal.ignoreCommand("1\n2\n3\n")
                        .newCommandLine(context)
                        .add("foo")
                        .execute()
                        .mapEachLine(Integer::valueOf)
                        .stream()
                        .mapToInt(i -> i)
                        .sum());
    }

}