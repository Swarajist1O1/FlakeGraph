class DummyClass_97701 {
    @Test
    public void basicTest() {
        final List<String> lines = new ArrayList<>();
        final EmitterExtension.Writer writer = new EmitterExtension.Writer() {
            @Override
            public void writeIndentedLine(String line) {
                lines.add(line);
            }

}