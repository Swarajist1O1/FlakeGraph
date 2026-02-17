class DummyClass_98670 {
    @Test
    public void test_upload_empty_just_r_n() throws Exception {
        MockHttpServletRequest req = Mock.servlet.request();
        req.setPathInfo("/nutz/junit/uploading");
        MultipartInputStream ins = Mock.servlet.insmulti(charset);
        File f = Files.findFile("org/nutz/mvc/upload/files/_r_n.txt");
        ins.append("theF", f);
        req.setInputStream(ins);
        req.init();

        /*
         * é»è®¤ä¸å¿½ç¥ç©ºæä»¶
         */
        Uploading up = UploadUnit.TYPE.born();
        Map<String, Object> map = up.parse(req, UploadingContext.create(tmps));
        assertEquals(1, map.size());
        TempFile tf = (TempFile) map.get("theF");

        assertEquals("_r_n.txt", tf.getSubmittedFileName());
        assertTrue(Streams.equals(Streams.fileIn(f), tf.getInputStream()));
    }

}