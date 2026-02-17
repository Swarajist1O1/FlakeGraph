class DummyClass_98631 {
    @Test
    public void test_upload() throws Throwable {
        Request req = Request.create(getBaseURL()+"/upload/image",METHOD.POST);
        File f = File.createTempFile("nutz", "data");
        FileWriter fw = new FileWriter(f);
        fw.write("abc");
        fw.flush();
        fw.close();
        req.getParams().put("file", f);
        FilePostSender sender = new FilePostSender(req);
        Response resp = sender.send();
        assertEquals("image&3", resp.getContent());
    }

}