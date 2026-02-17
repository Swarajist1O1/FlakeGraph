class DummyClass_98669 {
    @Test
    public void test_issue_1220() throws IOException {
        File f = File.createTempFile("abc_", ".json");
        org.nutz.lang.Files.write(f, "abc");
        File f2 = File.createTempFile("def_", ".json");
        org.nutz.lang.Files.write(f2, "def");
        upload("/upload/issue1220", new NutMap("file", new File[]{f, f2}));
        assertEquals(200, resp.getStatus());
        String cnt = resp.getContent();
        System.out.println(cnt);
        assertEquals("2,3,3", cnt);
    }

}