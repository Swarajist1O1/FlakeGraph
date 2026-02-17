class DummyClass_76744 {
    @Test
    public void testThatNewBeanAreDiscovered() throws IOException, MavenInvocationException {
        testDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-new-bean-remote");
        agentDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-run-new-bean-local");
        runAndCheck();

        // Edit the "Hello" message.
        File source = new File(agentDir, "src/main/java/org/acme/MyBean.java");
        String content = "package org.acme;\n" +
                "\n" +
                "import javax.enterprise.context.ApplicationScoped;\n" +
                "\n" +
                "@ApplicationScoped\n" +
                "public class MyBean {\n" +
                "\n" +
                "    public String get() {\n" +
                "        return \"message\";\n" +
                "    }\n" +

}