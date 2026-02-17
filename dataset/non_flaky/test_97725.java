class DummyClass_97725 {
    @Test
    public void test() throws IOException {
        final Settings settings = TestUtils.settings();
        settings.outputKind = TypeScriptOutputKind.module;
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.mapClasses = ClassMapping.asClasses;
        settings.extensions.add(new JsonDeserializationExtension());
        final File actualFile = new File("target/JsonDeserializationTest-actual.ts");
        new TypeScriptGenerator(settings).generateTypeScript(Input.from(User.class), Output.to(actualFile));
        final List<String> actualLines = Files.readAllLines(actualFile.toPath(), StandardCharsets.UTF_8);
        final List<String> expectedLines = Utils.readLines(getClass().getResourceAsStream("JsonDeserializationTest-expected.ts"));

        int contentLines = 0;
        int foundLines = 0;
        final List<String> notFoundLines = new ArrayList<>();
        for (String expectedLine : expectedLines) {
            if (!expectedLine.isEmpty() || !expectedLine.trim().equals("}")) {
                contentLines++;
                if (actualLines.contains(expectedLine)) {
                    foundLines++;
                } else {
                    notFoundLines.add(expectedLine);
                }
            }
        }

}