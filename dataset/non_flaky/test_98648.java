class DummyClass_98648 {
    @Test
    public void issue_1267() {
        resp = post("/adaptor/issue1267", new NutMap("time", "Thu May 25 2017 07:16:32 GMT+0800 (CST)"));
        assertEquals(200, resp.getStatus());
        System.out.println(resp.getContent());
        //assertEquals("1495667792000", resp.getContent());
    }

}