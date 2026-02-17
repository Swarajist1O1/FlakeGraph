class DummyClass_98674 {
    @Test(expected = UploadOutOfSizeException.class)
    public void test_limit_file_size_fail() throws UploadException {
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
        // å½è®¾ç½®ä¸º170,pass
        // è®¾ç½®ä¸º171,fail åå æªæ
        // zzh: FastUploading çéå¶ä¸æ¯ç¹å«ç²¾ç¡®
        // å ä¸ºæ¯æåè¯»åç, æ¯æ¬¡å¾ªç¯ï¼è¦è¯»1-3ä¸ªåï¼æä»¥å°ºå¯¸çéå¶å ç¼å²å¤§å°ï¼ä¹ä¼æå³ç³»
        // å¦æç¼å²æ¯ 171, å¯è½æ­£å¥½è¯»å®
        up.parse(req, UploadingContext.create(tmps)
                                      .setBufferSize(171)
                                      .setMaxFileSize(18620));
    }

}