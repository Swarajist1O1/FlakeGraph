class DummyClass_98625 {
    @Test
    public void test_issue_1307() {
        //assertTrue((Boolean)El.eval("0 == 0"));
        assertTrue((Boolean)El.eval("0 == 0.0"));
    }

}