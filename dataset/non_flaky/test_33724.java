class DummyClass_33724 {
    @Test
    public void testBug1763_2() {
        BaseResult<PageResult<CouponResult>> data = JSON.parseObject(jsonStr, new TypeReferenceBug1763_2<BaseResult<PageResult<T>>>(clazz){}.getType());

        Assert.assertTrue(data.isSuccess());
        Assert.assertTrue(data.getContent().getList().size() == 2);
        try {
            data.getContent().getList().get(0).getId();
        } catch (Throwable ex) {
            Assert.assertEquals(ex.getCause() instanceof ClassCastException, false);
        }
    }

}