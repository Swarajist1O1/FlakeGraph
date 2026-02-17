class DummyClass_99743 {
    @Test
    public void endToEnd() throws IOException
    {
        List<String> targetHosts = Lists.newArrayList("hosta", "hostb");
        File tmpDir = Files.createTempDirectory("testresulthandler").toFile();
        File queryDir = Files.createTempDirectory("queries").toFile();
        List<File> resultPaths = generateResultSets(targetHosts, tmpDir, queryDir, true, false);
        Compare.compare(queryDir.toString(), resultPaths.stream().map(File::toString).collect(Collectors.toList()));
    }

}