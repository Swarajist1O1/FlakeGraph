class DummyClass_98641 {
    @Test
    public void test_default_value() {
    	resp = get("/adaptor/default_value?abc=123");
    	assertEquals(200, resp.getStatus());
    	assertEquals("123", resp.getContent());
    	

    	resp = get("/adaptor/default_value");
    	assertEquals(200, resp.getStatus());
    	assertEquals("123456", resp.getContent());
    }

}