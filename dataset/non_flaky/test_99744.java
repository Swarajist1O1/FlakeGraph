class DummyClass_99744 {
    @Test
    public void endToEndQueryFailures() throws IOException
    {
        List<String> targetHosts = Lists.newArrayList("hosta", "hostb");
        File tmpDir = Files.createTempDirectory("testresulthandler").toFile();
        File queryDir = Files.createTempDirectory("queries").toFile();
        List<File> resultPaths = generateResultSets(targetHosts, tmpDir, queryDir, true,true);
        Compare.compare(queryDir.toString(), resultPaths.stream().map(File::toString).collect(Collectors.toList()));
    }

}