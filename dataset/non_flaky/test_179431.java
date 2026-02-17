class DummyClass_179431 {
    @Test
    public void testRoleMapper(){
        MapperHelper mapperHelper = new MapperHelper();
        Assert.assertFalse(mapperHelper.isExtendCommonMapper(RoleMapper.class));
    }

}