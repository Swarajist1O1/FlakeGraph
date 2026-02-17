class DummyClass_179430 {
    @Test
    public void testHashRegisterMapper(){
        MapperHelper mapperHelper = new MapperHelper();
        Assert.assertTrue(mapperHelper.isExtendCommonMapper(UserMapper.class));
    }

}