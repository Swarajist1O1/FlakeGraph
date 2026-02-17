class DummyClass_98671 {
    @Test(expected = UploadUnsupportedFileTypeException.class)
    public void test_limit_file_content_type_fail() throws UploadException {
        MockHttpServletRequest req = Mock.servlet.request();
        req.setPathInfo("/nutz/junit/uploading");
        File blue = Files.findFile("org/nutz/mvc/upload/files/quick/blue.png");

        MultipartInputStream ins = Mock.servlet.insmulti(charset);
        ins.append("blue", blue);
        req.setInputStream(ins);
        req.init();

        /*
         * æä»¶è¶å¤§ï¼ä¼éå¶
         */
        Uploading up = UploadUnit.TYPE.born();
        up.parse(req,
                 UploadingContext.create(tmps)
                                 .setContentTypeFilter("^image/gif$"));
    }

}