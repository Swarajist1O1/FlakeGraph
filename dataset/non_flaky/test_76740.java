class DummyClass_76740 {
    @Test
    public void testThatTheApplicationIsReloadedOnNewResource() throws MavenInvocationException, IOException {
        testDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-new-resource-remote");
        agentDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-new-resource-local");
        runAndCheck();

        File source = new File(agentDir, "src/main/java/org/acme/MyNewResource.java");
        String myNewResource = "package org.acme;\n" +
                "\n" +
                "import javax.ws.rs.GET;\n" +
                "import javax.ws.rs.Path;\n" +
                "import javax.ws.rs.Produces;\n" +
                "import javax.ws.rs.core.MediaType;\n" +
                "\n" +
                "@Path(\"/foo\")\n" +
                "public class MyNewResource {\n" +

                "    @GET\n" +
                "    @Produces(MediaType.TEXT_PLAIN)\n" +
                "    public String foo() {\n" +
                "        return \"bar\";\n" +
                "    }\n" +

}