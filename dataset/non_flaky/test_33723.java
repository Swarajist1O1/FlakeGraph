class DummyClass_33723 {
    @Test
    public void testFixBug1763_2() {
        BaseResult<PageResult<CouponResult>> data = JSON.parseObject(jsonStr, new TypeReference<BaseResult<PageResult<T>>>(clazz){}.getType());

        Assert.assertTrue(data.isSuccess());
        Assert.assertTrue(data.getContent().getList().size() == 2);
        Assert.assertTrue(data.getContent().getList().get(0).getId().equals(10000001L));
        Assert.assertEquals(CouponResult.class, data.getContent().getList().get(0).getClass());
    }

}