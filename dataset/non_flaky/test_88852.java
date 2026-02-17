class DummyClass_88852 {
    @Test
    public void testComputeWithCtx3() {
        wrapper.computeWithCtx((ctx, data) -> {
            assertNotNull(ctx);
            assertNotNull(data);
        });

        verify(dataset, times(1)).computeWithCtx(any(IgniteTriFunction.class),
            any(IgniteBinaryOperator.class), any());
    }

}