class DummyClass_98662 {
    @Test
    public void test_pathargs() {
        get("/common/pathArgs/Wendal");
        assertEquals("Wendal", resp.getContent());

        get("/common/pathArgs2/Wendal/12345/123456789/123/123.00/200.9/true/n");
        assertEquals("Wendal12345123456789123123200truen", resp.getContent());

        get("/common/pathArgs3/public/blog/200");
        assertEquals("public&200", resp.getContent());
        get("/common/pathArgs3/puZ");
        assertEquals("puZ&Z", resp.getContent());

        get("/common/pathArgs4/nutz?name=wendal");
        assertEquals("nutz&wendal", resp.getContent());

        get("/common/pathArgs5/nutz?user.name=wendal&user2.name=zozoh");
        assertEquals("nutz&wendal&zozoh", resp.getContent());
    }

}