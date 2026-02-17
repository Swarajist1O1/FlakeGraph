class DummyClass_43085 {
    @Test
    public void testColumnsInReverseOrder()
    {
        assertQuery("SELECT shippriority, clerk, totalprice FROM orders");
    }

}