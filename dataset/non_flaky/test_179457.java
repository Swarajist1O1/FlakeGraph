class DummyClass_179457 {
    @Test
    public void testCamelhumpToUnderline() {
        Assert.assertEquals("user_id", StringUtil.camelhumpToUnderline("userId"));
        Assert.assertEquals("sys_user", StringUtil.camelhumpToUnderline("sysUser"));
        Assert.assertEquals("sys_user_role", StringUtil.camelhumpToUnderline("sysUserRole"));
        Assert.assertEquals("s_function", StringUtil.camelhumpToUnderline("sFunction"));
    }

}